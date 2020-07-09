# [CS61B] Week 2 - Reading 2.2

Created: Jul 7, 2020 5:13 PM
Last edited time: Jul 8, 2020 6:18 PM
Modified Date: Jul 7, 2020 5:13 PM
Tags: Reading
Week: Week 2

# Improvement #1: Rebranding

`IntList`: naked recursive

change it much more closely resembles the list implementations in modern languages

- before:

```python
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
...
```

- new codes:

```python
public class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
        item = i;
        next = n;
    }
}
```

# Improvement #2: Bureaucracy

`SLList`

```python
public class SLList {
    public IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }
}
```

```python
IntList L1 = new IntList(5, null);
SLList L2  = new SLList(5);
```

# addFirst and getFirst

```python
public class SLList {
    public IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

		/** Retrieves the front item from the list. */
		public int getFirst() {
		    return first.item;
		}
}
```

```python
SLList L = new SLList(15);
L.addFirst(10);
L.addFirst(5);
int x = L.getFirst();
```

```python
IntList L = new IntList(15, null);
L = new IntList(10, L);
L = new IntList(5, L);
int x = L.first;
```

data structure

![CS61B%20Week%202%20Reading%202%202%20f401de0078ac44f8933011758f47b37c/Untitled.png](CS61B%20Week%202%20Reading%202%202%20f401de0078ac44f8933011758f47b37c/Untitled.png)

Essentially, the SLList class acts as a **middleman** between the list user and the naked recursive data structure. As suggested above in the IntList version, there is a **potentially undesireable possibility** for the IntList user to have variables that point to the middle of the IntList. As Ovid said: Mortals cannot look upon a god without dying, so perhaps it is best that the SLList is there to act as our intermediary.

# Improvement #3: Public vs. Private

![CS61B%20Week%202%20Reading%202%202%20f401de0078ac44f8933011758f47b37c/Untitled%201.png](CS61B%20Week%202%20Reading%202%202%20f401de0078ac44f8933011758f47b37c/Untitled%201.png)

```python
SLList L = new SLList(15);
L.addFirst(10);
L.first.next.next = L.first.next;
```

![CS61B%20Week%202%20Reading%202%202%20f401de0078ac44f8933011758f47b37c/Untitled%202.png](CS61B%20Week%202%20Reading%202%202%20f401de0078ac44f8933011758f47b37c/Untitled%202.png)

- the **private** keyword is an invaluable signal that certain pieces of code should be ignored (and thus need not be understood) by the end user.
- the **public** keyword should be thought of as a declaration that a method is available and will work forever exactly as it does now.

> When you create a public member (i.e. method or variable), be careful, because you're effectively committing to supporting that member's behavior exactly as it is now, forever.

# Improvement #4: Nested Classes

However, the `IntNode` is really just a supporting character in the story of `SLList`.

```python
public class SLList {
       public class IntNode {
            public int item;
            public IntNode next;
            public IntNode(int i, IntNode n) {
                item = i;
                next = n;
            }
       }

       private IntNode first; 

       public SLList(int x) {
           first = new IntNode(x, null);
       } 
...
```

Having a nested class has no meaningful effect on code performance, and is simply a tool for keeping code organized.

Declaring a nested class as static means that methods inside the static class can not access any of the members of the enclosing class.

```python
public class SLList {
       public static class IntNode {
            public int item;
            public IntNode next;
            public IntNode(int i, IntNode n) {
                item = i;
                next = n;
            }
       }

       private IntNode first;
...
```

This **saves a bit of memory**, because each IntNode no longer needs to keep track of how to access its enclosing SLList.

Put another way, if you examine the code above, you'll see that the IntNode class never uses the first variable of SLList, nor any of SLList's methods. As a result, w**e can use the `static` keyword, which means the IntNode class doesn't get a reference to its boss, saving us a small amount of memory.**

**A simple rule of thumb**: *if you don't use any instance members of the outer class, make the nested class static.*

# addLast() and size()

![CS61B%20Week%202%20Reading%202%202%20f401de0078ac44f8933011758f47b37c/Untitled%203.png](CS61B%20Week%202%20Reading%202%202%20f401de0078ac44f8933011758f47b37c/Untitled%203.png)