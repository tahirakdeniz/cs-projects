//
// Created by muham on 4.12.2022.
//

#ifndef ASM203_3_V2_CASHIER_H
#define ASM203_3_V2_CASHIER_H


#include "BaristaSystem.h"

class Cashier {
    Queue<Order>* orderQueue;
    BaristaSystem *baristaSystem = nullptr;
    Order *currentOrder = nullptr;
    float availableTime = 0;
    int id;
    float busyTime = 0;
public:
    explicit Cashier(Queue<Order> *orderQueue, int id);

    float getAvailableTime() const;
    void setBaristaSystem(BaristaSystem *newBaristaSystem);

    float getBusyTime() const;

    void takeOrder(Order *order);
    void fetchNewOrderFromQueue();


};
#endif //ASM203_3_V2_CASHIER_H
