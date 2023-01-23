//
// Created by muham on 4.12.2022.
//

#ifndef ASM203_3_V2_QUEUE_H
#define ASM203_3_V2_QUEUE_H
#define DEFAULT_SIZE 10

template <typename T>
class Queue {
    T** arr;
    int front;
    int rear;
    int capacity;
    int size;
public:
    Queue() {
        rear = -1;
        front = -1;
        capacity = DEFAULT_SIZE;
        size = 0;
        arr = new T*[capacity];
    }

    void enqueue(T* element) {
        if (size >= capacity) increaseArrayCapacity();

        rear = (rear + 1)%capacity;
        arr[rear] = element;
        if (front == -1) front = 0;
        size++;
    }


    T* dequeue() {
        if(size > 0) {
            T* result = arr[front];
            front = (front + 1)%capacity;
            size --;
            return result;
        }
    }

    bool isEmpty() {
        return size == 0;
    }

    int getSize() {
        return size;
    }

    virtual ~Queue() {
        delete[] arr;
    }

private:
    void increaseArrayCapacity() {

        // Create a new array
        int newCapacity = capacity * 2;
        T** newArr = new T*[newCapacity];

        // Copy old array to new array
        for (int i = 0; i < capacity; i++) {
            newArr[i] = arr[i];
        }

        // delete old array
        delete[] arr;

        // assign arr to new array
        arr = newArr;
        capacity = newCapacity;
    }
};

#endif //ASM203_3_V2_QUEUE_H
