(ns calendar.core
  (:require [clj-time.core :as t])) 

(defn overlapping? [event1 event2]
  (let [{st1 :start-time
         et1 :end-time} event1
        {st2 :start-time
         et2 :end-time} event2]
    (and (t/overlaps? st1 et1 st2 et2) (not= event1 event2))))

(defn get-overlapping [events event]
  (->> events
      (filter #(overlapping? event %))))

(defn all-overlapping [events]
  (->> events
       (map #(if (> (count (get-overlapping events %)) 0) (conj (get-overlapping events %) %)))
       (filter some?)
       (map #(sort-by :start-time %))
       (distinct)))
