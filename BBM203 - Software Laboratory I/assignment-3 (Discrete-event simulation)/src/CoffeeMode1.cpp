//
// Created by muham on 4.12.2022.
//

#include "CoffeeMode1.h"

CoffeeMode1::CoffeeMode1(int cashierNumber) : Coffee(cashierNumber) {
    baristaSystem = new BaristaSystem(cashierNumber/3);

    for(auto cashier : cashierArr) {
        cashier->setBaristaSystem(baristaSystem);
    }
}

int CoffeeMode1::getMaxSizeOfBaristaOrderQueue() const {
    return baristaSystem->getMaximumSizeOfQueue();
}

std::vector<float> CoffeeMode1::getBaristasBusyTime() const {
    return baristaSystem->getBaristasBusyTime();
}

CoffeeMode1::~CoffeeMode1() {
    delete baristaSystem;
}
