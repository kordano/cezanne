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
        geometry-0 (js/THREE.CubeGeometry. 1.7 1.7 1.7)
        geometry-1 (js/THREE.SphereGeometry. 1.5 8 8)
        geometry-2 (js/THREE.RingGeometry. 1.2 1.5 64)
        geometry-3 (js/THREE.SphereGeometry. 1 4 4)
        material-0 (js/THREE.MeshBasicMaterial. (clj->js {:wireframe true
                                                         :color 0xa00e39
                                                          :wireframeLinewidth 8}))
        material-1 (js/THREE.MeshBasicMaterial. (clj->js {:wireframe true
                                                         :color 0x066a9d
                                                         :wireframeLinewidth 3}))
        material-2 (js/THREE.MeshBasicMaterial. (clj->js {:wireframe true
                                                         :color 0x0ea039
                                                         :wireframeLinewidth 3}))
        material-3 (js/THREE.MeshBasicMaterial. (clj->js {:wireframe true
                                                         :color 0xf99a25
                                                         :wireframeLinewidth 3}))
        cube-0 (js/THREE.Mesh. geometry-0 material-0)
        sphere-0 (js/THREE.Mesh. geometry-1 material-1)
        sphere-1 (js/THREE.Mesh. geometry-3 material-2)
        ring-0 (js/THREE.Mesh. geometry-2 material-3)
        ]
    (.setSize renderer (.-innerWidth js/window) (.-innerHeight js/window))
    (.. js/document -body (appendChild (.-domElement renderer)))
    (.add scene cube-0)
    (.add scene sphere-0)
    (.add scene sphere-1)
    (.add scene ring-0)
    (set! (.. camera -position -z) 5)
    (set! (.. cube-0 -position -x) 4)
    (set! (.. ring-0 -position -x) -4)
    (set! (.. sphere-1 -position -x) -4)
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
  (set! (.. (:ring-0 @state) -rotation -x) (+ (.. (:ring-0 @state) -rotation -x) 4e-2))
  (set! (.. (:ring-0 @state) -rotation -y) (+ (.. (:ring-0 @state) -rotation -y) 8e-2))
  (set! (.. (:sphere-1 @state) -rotation -x) (+ (.. (:sphere-1 @state) -rotation -x) -4e-2))
  (set! (.. (:sphere-1 @state) -rotation -y) (+ (.. (:sphere-1 @state) -rotation -y) -4e-3))
  )


(init-all)
(render)
