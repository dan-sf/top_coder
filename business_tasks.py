"""
SRM 236
A busy businessman has a number of equally important tasks which he must
accomplish. To decide which of the tasks to perform first, he performs the
following operation.

He writes down all his tasks in the form of a circular list, so the first task
is adjacent to the last task. He then thinks of a positive number. This number
is the random seed, which he calls n. Starting with the first task, he moves
clockwise (from element 1 in the list to element 2 in the list and so on),
counting from 1 to n. When his count reaches n, he removes that task from the
list and starts counting from the next available task. He repeats this
procedure until one task remains. It is this last task that he chooses to
execute.

Given a String[] list representing the tasks and an int n, return the task
which the businessman chooses to execute.
"""

class Node(object):
    def __init__(self, data):
        self.data = data
        self.next = next

class LinkedList(object):
    def __init__(self):
        self.head = None
        self.size = 0

    def add(self, data):
        n = Node(data)
        n.next = self.head
        self.head = n
        self.size += 1

    def remove(self, index):
        current = self.head
        previous = None
        count = 1
        while current != None:
            if index == 1:
                current = current.next
                self.head = self.head.next
                break
            else:
                if count == index:
                    previous.next = current.next
            count += 1
            previous = current
            current = current.next
        self.size -= 1

    def print_list(self):
        current = self.head
        while current != None:
            print current.data,
            current = current.next
        print

class BusinessTasks(object):
    def __init__(self):
        self.ll = LinkedList()

    def load_tasks(self, tasks):
        tasks.reverse()
        for t in tasks:
            self.ll.add(t)

    def getTask(self, tasks, n):
        self.load_tasks(tasks)

        count = 1
        while self.ll.size > 1:
            current_index = 1
            current = self.ll.head
            while current != None and self.ll.size > 1:
                if count == n:
                    current = current.next
                    self.ll.remove(current_index)
                    count = 1
                    continue
                count += 1
                current_index += 1
                current = current.next
        return self.ll.head.data

    def getTaskFast(self, tasks, n):
        offset = 0
        while len(tasks) > 1:
            offset2 = (offset + (n-1)) % len(tasks)
            del tasks[offset2]
            offset = offset2 % len(tasks)

        return tasks[0]

if __name__ == '__main__':

    b = BusinessTasks()
    print b.getTaskFast(["a","b","c","d"], 2)
    # Returns: "a"
    b = BusinessTasks()
    print b.getTaskFast(["a","b","c","d","e"], 3)
    # Returns: "d"
    b = BusinessTasks()
    print b.getTaskFast(["a","b","c","d"], 2)
    # Returns: "a"
    b = BusinessTasks()
    print b.getTaskFast(["a","b","c","d","e"], 3)
    # Returns: "d"
    b = BusinessTasks()
    print b.getTaskFast(["alpha","beta","gamma","delta","epsilon"], 1)
    # Returns: "epsilon"
    b = BusinessTasks()
    print b.getTaskFast(["a","b"], 1000)
    # Returns: "a"
    b = BusinessTasks()
    print b.getTaskFast(["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t",
    "u","v","w","x","y","z"], 17)
    # Returns: "n"
    b = BusinessTasks()
    print b.getTaskFast(["zlqamum","yjsrpybmq","tjllfea","fxjqzznvg","nvhekxr","am","skmazcey","piklp",
    "olcqvhg","dnpo","bhcfc","y","h","fj","bjeoaxglt","oafduixsz","kmtbaxu",
    "qgcxjbfx","my","mlhy","bt","bo","q"], 9000000)
    # Returns: "fxjqzznvg"

