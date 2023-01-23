//
// Created by muham on 4.12.2022.
//

#include <iostream>
#include "BaristaSystem.h"

BaristaSystem::BaristaSystem(int baristaNumber) {
    queue = new PricePriorityQueue();

    for (int i = 0; i < baristaNumber; i++) {
        auto newBarista = new Barista(queue, i);
        baristaArr.push_back(newBarista);
    }
}

BaristaSystem::~BaristaSystem() {
    delete queue;
    for (auto barista : baristaArr) {
        delete barista;
    }
}

void BaristaSystem::addOrder(Order *order) {
    queue->enqueue(order);

    for(auto barista : baristaArr) {
        if(order->baristaArrivalTime >= barista->getAvailableTime()) {
            barista->takeOrder(queue->dequeue());
            return;
        }
    }

    maximumSizeOfQueue = std::max(maximumSizeOfQueue, queue->getSize());
}

int BaristaSystem::getMaximumSizeOfQueue() const {
    return maximumSizeOfQueue;
}

std::vector<float> BaristaSystem::getBaristasBusyTime() const {
    std::vector<float> v{};
    for(auto barista : baristaArr) {
        v.push_back(barista->getBusyTime());
    }
    return v;
}
