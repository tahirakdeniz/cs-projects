//
// Created by muham on 4.12.2022.
//

#ifndef ASM203_3_V2_PRICEPRIORTYQUEUE_H
#define ASM203_3_V2_PRICEPRIORTYQUEUE_H


#include <vector>
#include "Order.h"

class PricePriorityQueue {
    // TODO test this
    struct OrderNode {
        Order * order;
        OrderNode *next = nullptr;

        explicit OrderNode(Order *order);
    };
    int size = 0;
    OrderNode* head;
public:
    void enqueue(Order* order);
    Order* dequeue();
    bool isEmpty();

    int getSize() const;

    virtual ~PricePriorityQueue();
};


#endif //ASM203_3_V2_PRICEPRIORTYQUEUE_H
