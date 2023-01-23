//
// Created by muham on 4.12.2022.
// ASM203_3_V2_BARISTA_H
//

#ifndef ASM203_3_V2_BARISTA_H
#define ASM203_3_V2_BARISTA_H


#include "Order.h"
#include "PricePriortyQueue.h"

class Barista {


private:
    float availableTime = 0;
    PricePriorityQueue *queue = nullptr;
    float busyTime = 0;

public:
    explicit Barista(PricePriorityQueue *queue, int id);

    float getBusyTime() const;

    float getAvailableTime() const;

    void takeOrder(Order *order);
    void fetchNewOrder();
};


#endif //ASM203_3_V2_BARISTA_H
