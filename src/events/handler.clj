(ns events.handler
  (:use [compojure.core]
        [ring.middleware.json]
        [org.httpkit.server])
  (:require [ring.middleware.json :as middleware]
            [ring.util.response :as resp]
            [cheshire.core :as cheshire]
            [taoensso.timbre :as timbre]
            [compojure.route :as route]
            ))

(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (cheshire/generate-string data)})

(defroutes handler
  (GET "/" []
       (timbre/info "GET '/'")
       (resp/resource-response "index.html" {:root "public/"})
       )
  (PUT "/" [name]
       (println name)
       (timbre/info "PUT '/'" name)
       (json-response {"hello" name})
       )
  (route/resources "/")
  (route/not-found "Not Found")
  )

(def app
  (-> handler
      middleware/wrap-json-params))

(defn string->number [str]
  (let [n (read-string str)]
    (if (number? n) n nil)))

(defn server [port]
  (let [n (string->number port)]
  (timbre/info "running on port" port)
  (run-server app {:port n})))
