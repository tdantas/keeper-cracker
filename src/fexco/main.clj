(ns fexco.main
  (:gen-class)
  (:require [clojure.java.io :as io]))

;; - cpu intensive task

(defn- try-fn [plain encrypted]
  (Thread/sleep 50)
  (> (rand-int 1000) 998))

(defn- crack [encrypted-password lines]
  (some #(when (try-fn % encrypted-password) %) lines))

;; - I'm partitioning ( batch size 100 lines ) the lines to reduce the thread creation overhead for each line
;; - pmap will run N cpu + 2 which is great for our usecase, we have some "bounded" number of threads
;; - I'm stopping the process as soon as we found the password

(defn run [filename encrypted-password]
  (with-open [rdr (io/reader (io/file filename))]
    (->> (partition-all 100 (line-seq rdr))
         (pmap #(crack encrypted-password %))
         (reduce #(when (seq %2) (reduced %2)) {}))))


;; we could validate the filename / encrypted password
;; but for simplicity I'm assuming the inputs are correct

(defn -main [& args]
  (let [[filename encrypted-password] args]
    (println "Password: " (run filename encrypted-password))))

(comment

  "USAGE"

  ;; previous solution I tried to use my own lazy-seq ( shame on me )
  ;; currently I'm using the line-seq and trimming the amount of code written

  ;; also I'm not using any shared state between threads ( previous solution I was using an atom )
  ;; which makes the code much more simpler and easy to read

  (run "passwords.txt" "some-encrypted-password"))
