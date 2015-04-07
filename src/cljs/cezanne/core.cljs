(ns cezanne.core)

(enable-console-print!)

(.log js/console "Kneel before the holy Kordano!")


(def state (atom {}))


(defn init-all []
  (let [scene (js/THREE.Scene.)
        camera (js/THREE.PerspectiveCamera.
                    75
                    (/ (.-innerWidth js/window) (.-innerHeight js/window))
                    0.1
                    1000)
        renderer (js/THREE.WebGLRenderer.)
        geometry (js/THREE.BoxGeometry. 1 1 1)
        material (js/THREE.MeshBasicMaterial. (clj->js {:color 0x5555ff}))
        cube (js/THREE.Mesh. geometry material)]
    (.setSize renderer (.-innerWidth js/window) (.-innerHeight js/window))
    (.. js/document -body (appendChild (.-domElement renderer)))
    (.add scene cube)
    (set! (.. camera -position -z) 5)
    (reset! state
          {:scene scene
           :camera camera
           :cube cube
           :renderer renderer})))



(defn render []
  (js/requestAnimationFrame render)
  (.render (:renderer @state) (:scene @state) (:camera @state))
  (set! (.. (:cube @state) -rotation -x) (+ (.. (:cube @state) -rotation -x) 0.1))
  (set! (.. (:cube @state) -rotation -y) (+ (.. (:cube @state) -rotation -y) 0.1))
  )


(init-all)
(render)
