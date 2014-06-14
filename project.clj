(defproject events "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [ring/ring-core "1.3.0"]
                 [ring/ring-json "0.3.1"]
                 [cheshire "5.3.1"]
                 [http-kit "2.1.18"]
                 [com.taoensso/timbre "3.2.1"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler events.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}}
  :main events.handler/server)
