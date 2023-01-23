//
// Created by muham on 4.12.2022.
//

#ifndef ASM203_3_V2_BARISTASYSTEM_H
#define ASM203_3_V2_BARISTASYSTEM_H


#include <vector>
#include "Queue.h"
#include "Order.h"
#include "Barista.h"
#include "PricePriortyQueue.h"

/**
 * a class to manage baristas and barista queues
 */
class BaristaSystem {
    PricePriorityQueue * queue;
    std::vector<Barista*> baristaArr{};
    int maximumSizeOfQueue = 0;
public:
    explicit BaristaSystem(int baristaNumber);
    void addOrder(Order* order);
    int getMaximumSizeOfQueue() const;
    std::vector<float> getBaristasBusyTime() const;
    virtual ~BaristaSystem();
};


#endif //ASM203_3_V2_BARISTASYSTEM_H
