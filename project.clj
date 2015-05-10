(defproject cezanne "0.1.0-SNAPSHOT"
  :description "Webgl Example"
  :url "https://github.com/kordano/cezanne"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/cljs" "src/clj"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3211"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [ring "1.3.2"]
                 [enlive "1.1.5"]
                 [compojure "1.3.3"]
                 [http-kit "2.1.19"]
                 [com.taoensso/timbre "3.4.0"]]
  :min-lein-version "2.0.0"
  :main cezanne.core
  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-figwheel "0.3.1"]]
  :clean-targets ^{:protect false}["resources/public/js/compiled"]
  :cljsbuild
  {:builds
   [{:source-paths ["src/cljs"]
     :figwheel { :on-jsload "cezanne.core/reload-hook" }
     :compiler
     {:output-to "resources/public/js/compiled/main.js"
      :output-dir "resources/public/js/compiled/out"
      :main "cezanne.core"
      :asset-path "js/compiled/out"
      :externs ["resources/public/static/three/three.min.js"]
      :optimizations :none
      :pretty-print false
      :source-map "main.js.map"}}]})
