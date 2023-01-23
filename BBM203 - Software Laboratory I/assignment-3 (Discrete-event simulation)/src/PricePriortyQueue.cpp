//
// Created by muham on 4.12.2022.
//

#include <iostream>
#include "PricePriortyQueue.h"

void PricePriorityQueue::enqueue(Order *order) {

    auto orderNode = new OrderNode(order);
    size +=1;

    // if head is null then assign to head
    if(!head){
        head = orderNode;
        return;
    }

    // if order price is bigger than head's order price, then head will be new order.
    if(order->price > head->order->price) {
        orderNode->next = head;
        head = orderNode;
        return;
    }


    OrderNode * node = head;
    while(node) {
        // if next is null then add new node after the node because order price is bigger than node.
        if(!node->next) {
            node->next = orderNode;
            return;
        }

        // if price is bigger than price of the next node then add new node after the current node
        if(order->price > node->next->order->price) {
            orderNode->next = node->next;
            node->next = orderNode;
            return;
        }

        // if price is smaller or equal to the price of the next node,
        // then continue to traverse next node to add new node after next node.
        node= node->next;
    }

}

Order *PricePriorityQueue::dequeue() {
    OrderNode* next = head->next;
    OrderNode* temp = head;
    Order *order = temp->order;
    head = next;

    size-=1;
    delete temp;
    return order;
}

bool PricePriorityQueue::isEmpty() {
    return size == 0;
}

int PricePriorityQueue::getSize() const {
    return size;
}

PricePriorityQueue::~PricePriorityQueue() {
    OrderNode* node = head;
    while(node) {
        OrderNode* temp = node->next;
        delete node;
        node = temp;
    }
}

PricePriorityQueue::OrderNode::OrderNode(Order *order) : order(order) {}
