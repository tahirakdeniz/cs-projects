//
// Created by muham on 4.12.2022.
//

#ifndef ASM203_3_V2_COFFEEMODE2_H
#define ASM203_3_V2_COFFEEMODE2_H


#include <vector>
#include "BaristaSystem.h"
#include "Coffee.h"

class CoffeeMode2 : public Coffee{
    std::vector<BaristaSystem*> baristaSystemArr;
public:
    explicit CoffeeMode2(int cashierNumber);
    std::vector<int> getMaxSizeOfBaristaOrderQueue() const;

    std::vector<float> getBaristasBusyTime() const override;

    ~CoffeeMode2() override;
};


#endif //ASM203_3_V2_COFFEEMODE2_H
