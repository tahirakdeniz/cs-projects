//
// Created by muham on 4.12.2022.
//

#include "Cashier.h"
#include "DiscreteEventSystem.h"

void Cashier::setBaristaSystem(BaristaSystem *newBaristaSystem) {
    baristaSystem = newBaristaSystem;
}

void Cashier::fetchNewOrderFromQueue() {

    if(!orderQueue->isEmpty()) {
        Order* order = orderQueue->dequeue();

        takeOrder(order);
    }
}

void Cashier::takeOrder(Order *order) {

    // if the cashier is available, then orderArr arrivalTime will be greater than availableTime. and new availableTime will be arrivalTime + availableTime
    // but if the cashier is not available, then availableTime will be greater than arrivalTime.
    availableTime = std::max(order->arrivalTime, availableTime) + order->orderTime;
    //currentOrder = order;

    // set barista ArrivalTime of the order
    order->baristaArrivalTime = availableTime;

    // set busy time of the cashier
    busyTime += order->orderTime;

    // Schedule completing time for orderArr:
    DiscreteEventSystem::getInstance()->enqueueEvent(availableTime, [this, order](){fetchNewOrderFromQueue(); baristaSystem->addOrder(order);});


}

float Cashier::getAvailableTime() const {
    return availableTime;
}

Cashier::Cashier(Queue<Order> *orderQueue, int id) : orderQueue(orderQueue), id(id) {}

float Cashier::getBusyTime() const {
    return busyTime;
}
