//
// Created by muham on 4.12.2022.
//

#ifndef ASM203_3_V2_COFFEE_H
#define ASM203_3_V2_COFFEE_H


#include <vector>
#include "Cashier.h"

class Coffee {
protected:
    std::vector<Cashier*> cashierArr;
    Queue<Order>* orderQueue;
    int maxSizeOfOrderQueue = 0;
public:
    explicit Coffee(int cashierNumber);
    void addOrder(Order* order);

    int getMaxSizeOfCashierOrderQueue() const;
    std::vector<float> getCashiersBusyTime() const;
    virtual std::vector<float> getBaristasBusyTime() const = 0;
    virtual ~Coffee();

};


#endif //ASM203_3_V2_COFFEE_H
