(ns calendar.core
  (:require [clj-time.core :as t])) 

(defn overlapping? [event1 event2]
  (let [{st1 :start-time
         et1 :end-time} event1
        {st2 :start-time
         et2 :end-time} event2]
    (t/overlaps? st1 et1 st2 et2)))

(defn get-overlapping [events event]
  (->> events
      (filter #(overlapping? event %))))
