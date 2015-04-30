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
        cube-geo-0 (js/THREE.CubeGeometry. 1.7 1.7 1.7)
        sphere-geo-0 (js/THREE.SphereGeometry. 1.3 16 16)
        sphere-geo-1 (js/THREE.SphereGeometry. 0.8 16 16)
        ring-geo-0 (js/THREE.RingGeometry. 1.2 1.5 64)
        material-0 (js/THREE.MeshBasicMaterial. (clj->js {:wireframe true :color 0xa00e39 :wireframeLinewidth 8}))
        material-1 (js/THREE.MeshBasicMaterial. (clj->js {:wireframe true :color 0x0ea039 :wireframeLinewidth 3}))
        material-2 (js/THREE.MeshBasicMaterial. (clj->js {:wireframe true :color 0x066a9d :wireframeLinewidth 3}))
        material-3 (js/THREE.MeshBasicMaterial. (clj->js {:wireframe true :color 0xf99a25 :wireframeLinewidth 3}))
        cube-0 (js/THREE.Mesh. cube-geo-0 material-0)
        sphere-0 (js/THREE.Mesh. sphere-geo-0 material-1)
        sphere-1 (js/THREE.Mesh. sphere-geo-1 material-2)
        ring-0 (js/THREE.Mesh. ring-geo-0 material-3)
        ]
    (.setSize renderer (.-innerWidth js/window) (.-innerHeight js/window))
    (.. js/document -body (appendChild (.-domElement renderer)))
    (.add scene cube-0)
    (.add scene sphere-0)
    (.add scene sphere-1)
    (.add scene ring-0)
    (set! (.. camera -position -z) 5)
    (set! (.. cube-0 -position -x) 4)
    (set! (.. sphere-0 -position -x) -4)
    (reset! state
          {:scene scene
           :camera camera
           :cube-0 cube-0
           :sphere-0 sphere-0
           :sphere-1 sphere-1
           :ring-0 ring-0
           :renderer renderer})))



(defn render []
  (js/requestAnimationFrame render)
  (.render (:renderer @state) (:scene @state) (:camera @state))
  (set! (.. (:cube-0 @state) -rotation -x) (+ (.. (:cube-0 @state) -rotation -x) 8e-3))
  (set! (.. (:cube-0 @state) -rotation -y) (+ (.. (:cube-0 @state) -rotation -y) 4e-2))
  (set! (.. (:sphere-0 @state) -rotation -x) (+ (.. (:sphere-0 @state) -rotation -x) 2e-2))
  (set! (.. (:sphere-0 @state) -rotation -y) (+ (.. (:sphere-0 @state) -rotation -y) 1e-2))
  (set! (.. (:ring-0 @state) -rotation -x) (+ (.. (:ring-0 @state) -rotation -x) 2.1e-2))
  (set! (.. (:ring-0 @state) -rotation -y) (+ (.. (:ring-0 @state) -rotation -y) 7.3e-2))
  (set! (.. (:sphere-1 @state) -rotation -x) (+ (.. (:sphere-1 @state) -rotation -x) -4e-2))
  (set! (.. (:sphere-1 @state) -rotation -y) (+ (.. (:sphere-1 @state) -rotation -y) -4e-3))
  )


(init-all)
(render)
