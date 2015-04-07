(ns cezanne.core)

(enable-console-print!)

(.log js/console "Kneel before the holy Kordano!")

(defn init-all []
  (let [scene (js/THREE.Scene.)
        camera (js/THREE.PerspectiveCamera.
                    75
                    (/ (.-innerWidth js/window) (.-innerHeight js/window))
                    0.1
                    1000)
        renderer (js/THREE.WebGLRenderer.)
        geometry (js/THREE.BoxGeometry. 1 1 1)
        material (js/THREE.MeshBasicMaterial. {:color "0x00ff00"})
        cube (js/THREE.Mesh. geometry material)]
    (.setSize renderer (.-innerWidth js/window) (.-innerHeight js/window))
    (.. js/document -body (appendChild (.-domElement renderer)))
    (.add scene cube)
    (set! (.. camera -position -z) 5)
    {:scene scene
     :camera camera
     :renderer renderer}))


(js/THREE.WebGLRenderer.)

(defn render [{:keys [scene camera renderer] :as state}]
  (js/requestAnimationFrame render state)
  (.render renderer scene camera))


(let [init (init-all)]
  (render init))
