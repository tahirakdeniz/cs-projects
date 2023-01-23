//
// Created by muham on 4.12.2022.
//

#include <iostream>
#include "Coffee.h"

Coffee::Coffee(int cashierNumber) {
    orderQueue = new Queue<Order>();

    for (int i = 0; i < cashierNumber; i++) {
        auto newCashier = new Cashier(orderQueue, i);
        cashierArr.push_back(newCashier);
    }


}

Coffee::~Coffee() {
    for (auto cashier : cashierArr) {
        delete cashier;
    }

    delete orderQueue;
}

void Coffee::addOrder(Order *order) {
    // Assume the function works when the time customer comes.

    // traverse the cashier array and if there exists an available cashier, give orderArr task to cashier
    for (auto cashier : cashierArr) {

        // note: if the cashier is available, then orderArr->arrivalTime >= cashier available time.
        if(order->arrivalTime >= cashier->getAvailableTime()) {
            cashier->takeOrder(order);
            return;
        }
    }

    // If there doesn't exist any available cashier, then add orderArr to queue
    orderQueue->enqueue(order);

    maxSizeOfOrderQueue = std::max(maxSizeOfOrderQueue, orderQueue->getSize());
}

int Coffee::getMaxSizeOfCashierOrderQueue() const {
    return maxSizeOfOrderQueue;
}

std::vector<float> Coffee::getCashiersBusyTime() const {
    std::vector<float> v{};

    for(auto cashier : cashierArr)
        v.push_back(cashier->getBusyTime());

    return v;
}



