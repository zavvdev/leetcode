(comment "
         1

   2           3
4     5    6      7
          

Inorder traversal result: [4 2 5 1 6 3 7]
Preorder traversal result: [1 2 4 5 3 6 7]
Postorder traversal result: [4 5 2 6 7 3 1]
          
------------------------------

Algorithm Inorder(tree):

Traverse the left subtree, i.e., call Inorder(left->subtree)
Visit the root.
Traverse the right subtree, i.e., call Inorder(right->subtree)
          
------------------------------
          
Algorithm Preorder(tree):

Visit the root.
Traverse the left subtree, i.e., call Preorder(left->subtree)
Traverse the right subtree, i.e., call Preorder(right->subtree) 

------------------------------

Algorithm Postorder(tree):

Traverse the left subtree, i.e., call Postorder(left->subtree)
Traverse the right subtree, i.e., call Postorder(right->subtree)
Visit the root
          ")

(ns tree-traversal)

(def tree {:val 1
           :left {:val 2
                  :left {:val 4
                         :left nil
                         :right nil}
                  :right {:val 5
                          :left nil
                          :right nil}}
           :right {:val 3
                   :left {:val 6
                          :left nil
                          :right nil}
                   :right {:val 7
                           :left nil
                           :right nil}}})

(defn inorder
  ([tree]
   (inorder tree []))
  ([tree res]
   (if (not (nil? tree))
     (inorder
      (tree :right)
      (conj
       (inorder
        (tree :left) res)
       (tree :val)))
     res)))

(defn preorder
  ([tree]
   (preorder tree []))
  ([tree res]
   (if (not (nil? tree))
     (preorder
      (tree :right)
      (preorder
       (tree :left)
       (conj res (tree :val))))
     res)))

(defn postorder
  ([tree]
   (postorder tree []))
  ([tree res]
   (if (not (nil? tree))
     (conj
      (postorder
       (tree :right)
       (postorder
        (tree :left) res)) (tree :val))
     res)))

(assert (= (inorder tree) [4 2 5 1 6 3 7]))
(assert (= (preorder tree) [1 2 4 5 3 6 7]))
(assert (= (postorder tree) [4 5 2 6 7 3 1]))