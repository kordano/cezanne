(ns cezanne.core
  (:gen-class :main true)
  (:require [taoensso.timbre :as timbre :refer [info debug error warn]]
            [compojure.route :refer [resources]]
            [compojure.core :refer [GET defroutes]]
            [compojure.handler :refer [site]]
            [clojure.java.io :as io]
            [org.httpkit.server :refer [with-channel on-receive on-close run-server send!]]))

(timbre/refer-timbre)

(defroutes handler
  (resources "/")
  (GET "/three" [] (io/resource "public/three.html"))
  (GET "/raw" [] (io/resource "public/raw.html")))

(defn -main [& args]
  (info "SERVER - Warming up...")
  (run-server (site #'handler) {:port 8082 :join? false})
  (info "SERVER - running!")
  (info  (str "Visit http://localhost:" 8082)))

(comment

  (def stop-server (run-server (site #'handler) {:port 8091 :join? false}))

  (stop-server)

  )
