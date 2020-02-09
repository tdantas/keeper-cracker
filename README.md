# Fexco

#### Challenge

```
Assuming a large (> 2GB) dictionary of passwords (one per line), write a dictionary-attacker in Clojure. 

You can use any built-in Java-9/Clojure-1.10/Linux facilities to solve the problem, but no external libraries. 

If you can describe a high-level approach that should work, but are not quite sure of how to put the code together, that's perfectly acceptable too. 

The top-level/public API should be just this function below. For the sake of simplicity, put all your code in a single namespace. 
(defn dictionary-attack [try-fn ...] ....)

Its first argument (`try-fn` predicate), you don't need to write, as its sole purpose is to return true/false (match or no-match).  

```

#### Solution

```
$ clj -m fexco.main passwords.txt some-encrypted-password
```

#### Plain text password file

plain text passwords file

```
$ seq 1 10000 > passwords.txt
```
