//
// Created by muham on 4.12.2022.
//

#include "CoffeeMode2.h"

CoffeeMode2::CoffeeMode2(int cashierNumber) : Coffee(cashierNumber) {
    for (int i = 0; i < cashierArr.size(); i+=3) {
        auto bs = new BaristaSystem(1);

        cashierArr[i]->setBaristaSystem(bs);
        cashierArr[i+1]->setBaristaSystem(bs);
        cashierArr[i+2]->setBaristaSystem(bs);

        baristaSystemArr.push_back(bs);
    }
}

std::vector<int> CoffeeMode2::getMaxSizeOfBaristaOrderQueue() const {
    std::vector<int> v{};

    for(auto baristaSystem: baristaSystemArr)
        v.push_back(baristaSystem->getMaximumSizeOfQueue());

    return v;
}

std::vector<float> CoffeeMode2::getBaristasBusyTime() const {
    std::vector<float> v{};
    for(auto baristaSystem: baristaSystemArr) {
        std::vector<float> baristasBusyTime = baristaSystem->getBaristasBusyTime();
        v.insert(v.end(), baristasBusyTime.begin(), baristasBusyTime.end());
    }

    return v;
}

CoffeeMode2::~CoffeeMode2() {
    for(auto baristaSystem: baristaSystemArr) {
        delete baristaSystem;
    }
}
