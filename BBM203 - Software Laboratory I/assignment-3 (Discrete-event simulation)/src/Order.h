//
// Created by muham on 4.12.2022.
//

#ifndef ASM203_3_V2_ORDER_H
#define ASM203_3_V2_ORDER_H

struct Order {
    const float arrivalTime;
    const float orderTime;
    const float brewTime;
    const float price;
    float baristaArrivalTime = 0;
    float completionTime = 0;

    Order(float arrivalTime, float orderTime, float brewTime, float price):arrivalTime(arrivalTime), orderTime(orderTime), brewTime(brewTime), price(price) {}
};

#endif //ASM203_3_V2_ORDER_H
