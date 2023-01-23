//
// Created by muham on 4.12.2022.
//

#include "Barista.h"
#include "DiscreteEventSystem.h"

Barista::Barista(PricePriorityQueue *queue, int id) : queue(queue) {}

float Barista::getAvailableTime() const {
    return availableTime;
}

void Barista::takeOrder(Order *order) {

    availableTime = std::max(order->baristaArrivalTime, availableTime) + order->brewTime;
    //currentOrder = order;

    // set barista compilation Time of the order
    order->completionTime = availableTime;

    // update busyTime
    busyTime += order->brewTime;

    // Schedule completing time for orderArr:
    DiscreteEventSystem::getInstance()->enqueueEvent(availableTime, [this](){ fetchNewOrder();});
}

void Barista::fetchNewOrder() {
    if(!queue->isEmpty())
        takeOrder(queue->dequeue());
}

float Barista::getBusyTime() const {
    return busyTime;
}
