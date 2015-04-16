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
        geometry (js/THREE.CubeGeometry. 2 2 2)
        material (js/THREE.MeshNormalMaterial. )
        cube (js/THREE.Mesh. geometry material)
        cube-2 (js/THREE.Mesh. geometry material)
        cube-4 (js/THREE.Mesh. geometry material)
        cube-3 (js/THREE.Mesh. geometry material)]
    (.setSize renderer (.-innerWidth js/window) (.-innerHeight js/window))
    (.. js/document -body (appendChild (.-domElement renderer)))
    (.add scene cube)
    (.add scene cube-2)
    (.add scene cube-3)
    (.add scene cube-4)
    (set! (.. camera -position -z) 5)
    (reset! state
          {:scene scene
           :camera camera
           :cube cube
           :cube-2 cube-2
           :cube-3 cube-3
           :cube-4 cube-4
           :renderer renderer})))



(defn render []
  (js/requestAnimationFrame render)
  (.render (:renderer @state) (:scene @state) (:camera @state))
  (set! (.. (:cube @state) -rotation -x) (+ (.. (:cube @state) -rotation -x) 1e-2))
  (set! (.. (:cube @state) -rotation -y) (+ (.. (:cube @state) -rotation -y) 8e-2))
  (set! (.. (:cube-2 @state) -rotation -x) (+ (.. (:cube-2 @state) -rotation -x) 8e-2))
  (set! (.. (:cube-2 @state) -rotation -y) (+ (.. (:cube-2 @state) -rotation -y) 1e-2))
  (set! (.. (:cube-3 @state) -rotation -x) (+ (.. (:cube-3 @state) -rotation -x) 4e-2))
  (set! (.. (:cube-3 @state) -rotation -y) (+ (.. (:cube-3 @state) -rotation -y) 1e-2))
  (set! (.. (:cube-4 @state) -rotation -x) (+ (.. (:cube-4 @state) -rotation -x) 1e-2))
  (set! (.. (:cube-4 @state) -rotation -y) (+ (.. (:cube-4 @state) -rotation -y) 4e-2))
  )


(init-all)
(render)
