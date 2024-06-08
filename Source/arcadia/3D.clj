(ns arcadia.3D
  (:import 
    [Godot Node GD Node3D Vector3 CharacterBody3D]))


(defn ^Vector3 global-position [^Node3D o]
  (.GlobalPosition o))

(defn ^Vector3 position [^Node3D o]
  (.Position o))

(defn ^Vector3 global-position! [ o ^Vector3 v]
  (set! (.GlobalPosition o) v))

(defn ^Vector3 position! [^Node3D o ^Vector3 v]
  (set! (.Position o) v))



(defn ^Vector3 scale [^Node3D o]
  (.Scale o))

(defn ^Vector3 scale! [^Node3D o ^Vector3 v]
  (set! (.Scale o) v))

(defn ^Vector3 move-and-slide
  "Calls the `.MoveAndSlide` method on a `CharacterBody3D`.
   This function exists because `(.MoveAndSlide ...)` requires
   that all C# optional parameters are provided."
  [^CharacterBody3D o
   ^Vector3 v
   & {:keys [floor-normal
             stop-on-slope?
             max-slides
             floor-max-angle
             infinite-inertia?]
      :or {floor-normal (Vector3.)
           stop-on-slope? false
           max-slides 4
           floor-max-angle 0.785398
           infinite-inertia? true}}]
  (.MoveAndSlide o
                 v
                 floor-normal
                 stop-on-slope?
                 max-slides
                 floor-max-angle
                 infinite-inertia?))
