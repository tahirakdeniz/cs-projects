//
// Created by muham on 4.12.2022.
//

#ifndef ASM203_3_V2_COFFEEMODE1_H
#define ASM203_3_V2_COFFEEMODE1_H


#include "Coffee.h"

class CoffeeMode1 : public Coffee{
    BaristaSystem* baristaSystem;
public:
    explicit CoffeeMode1(int cashierNumber);
    int getMaxSizeOfBaristaOrderQueue() const;

    std::vector<float> getBaristasBusyTime() const override;

    ~CoffeeMode1() override;
};


#endif //ASM203_3_V2_COFFEEMODE1_H
