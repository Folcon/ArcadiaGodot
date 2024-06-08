(ns arcadia.linear
  (:import 
    [Godot Vector3 Vector2]))

(defn v3 
  ([] (Vector3.))
  ([x y z] (Vector3. x y z)))

(defn v3* [^Vector3 v n]
  (Vector3. 
    (* (.X v) n)
    (* (.Y v) n)
    (* (.Z v) n)))

(defn v3+ 
  ([^Vector3 a ^Vector3 b]
    (Vector3. 
      (+ (.X a) (.X b))
      (+ (.Y a) (.Y b))
      (+ (.Z a) (.Z b))))
  ([^Vector3 a b & more]
    (reduce v3+ (v3+ a b) more)))

(defn v3- 
  ([^Vector3 a ^Vector3 b]
    (Vector3. 
      (- (.X a) (.X b))
      (- (.Y a) (.Y b))
      (- (.Z a) (.Z b))))
  ([^Vector3 a b & more]
    (reduce v3- (v3- a b) more)))

(defn v3div 
  ([^Vector3 a n]
    (Vector3. 
      (/ (.X a) n)
      (/ (.Y a) n)
      (/ (.Z a) n)))
  ([^Vector3 a b & more]
    (reduce v3div (v3div a b) more)))

(defn v2
  ([] (Vector2.))
  ([x y] (Vector2. x y)))

(defn v2* [^Vector2 v n]
  (Vector2. 
    (* (.X v) n)
    (* (.Y v) n)))

(defn v2+ 
  ([^Vector2 a ^Vector2 b]
    (Vector2. 
      (+ (.X a) (.X b))
      (+ (.Y a) (.Y b))))
  ([^Vector2 a b & more]
    (reduce v2+ (v2+ a b) more)))

(defn v2- 
  ([^Vector2 a ^Vector2 b]
    (Vector2. 
      (- (.X a) (.X b))
      (- (.Y a) (.Y b))))
  ([^Vector2 a b & more]
    (reduce v2- (v2- a b) more)))

(defn v2div
  ([^Vector2 a n]
    (Vector2. 
      (/ (.X a) n)
      (/ (.Y a) n)))
  ([^Vector2 a b & more]
    (reduce v2div (v2div a b) more)))