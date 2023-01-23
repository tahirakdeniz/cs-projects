//
// Created by muham on 6.12.2022.
//

#ifndef ASM203_3_V2_APP_H
#define ASM203_3_V2_APP_H
#include <iostream>
#include <fstream>
#include <vector>
#include "Order.h"
#include "DiscreteEventSystem.h"

class App {
    std::fstream inputStream{};
    std::ofstream outputStream{};

    int numberOfCashier = 0;
    int numberOfOrders = 0;

    DiscreteEventSystem* discreteEventSystem;
    std::vector<Order*> orderArr{};
public:
    App(std::string &inputFileName, std::string &outputFileName);
    void run();

    virtual ~App();

private:
    void readInput();

    // Helper methods
    static std::string round(float number);
};


#endif //ASM203_3_V2_APP_H
