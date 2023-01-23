//
// Created by muham on 6.12.2022.
//

#include "App.h"
#include "DiscreteEventSystem.h"
#include "CoffeeMode1.h"
#include "CoffeeMode2.h"
#include <iostream>
#include <sstream>

App::App(std::string &inputFileName, std::string &outputFileName) {

    inputStream.open(inputFileName);
    outputStream.open(outputFileName);
    discreteEventSystem = DiscreteEventSystem::getInstance();
}

void App::run() {
    readInput();
    float lastEventTime;

    // //////////////////////// COFFEE MODE 1 ////////////////////////
    CoffeeMode1 coffeeMode1(numberOfCashier);
    for(auto order: orderArr) {
        discreteEventSystem->enqueueEvent(order->arrivalTime, [&coffeeMode1, order](){coffeeMode1.addOrder(order);});
    }

    while (!discreteEventSystem->isEmpty()) {
        discreteEventSystem->dequeueEvent();
    }

    lastEventTime = discreteEventSystem->getLastEventTime();

    outputStream << round(lastEventTime) << std::endl;
    outputStream << coffeeMode1.getMaxSizeOfCashierOrderQueue() << std::endl;
    outputStream << coffeeMode1.getMaxSizeOfBaristaOrderQueue() << std::endl;

    for(float cashierBusyTime:coffeeMode1.getCashiersBusyTime())
        outputStream << round(cashierBusyTime/lastEventTime) << std::endl;

    for(float baristaBusyTime:coffeeMode1.getBaristasBusyTime())
        outputStream << round(baristaBusyTime/lastEventTime) << std::endl;

    for(auto order:orderArr)
        outputStream << round(order->completionTime - order->arrivalTime) << std::endl;

    outputStream << std::endl;
    // ////////////////////////////////////////////////////////////////

    DiscreteEventSystem::deleteInstance();
    discreteEventSystem = DiscreteEventSystem::getInstance();

    // //////////////////////// COFFEE MODE 2 ////////////////////////


    CoffeeMode2 coffeeMode2(numberOfCashier);

    for(auto order: orderArr) {
        discreteEventSystem->enqueueEvent(order->arrivalTime, [&coffeeMode2, order](){coffeeMode2.addOrder(order);});
    }

    while (!discreteEventSystem->isEmpty()) {
        discreteEventSystem->dequeueEvent();
    }

    lastEventTime = discreteEventSystem->getLastEventTime();

    outputStream << round(lastEventTime) << std::endl;
    outputStream << coffeeMode2.getMaxSizeOfCashierOrderQueue() << std::endl;

    for(auto order: orderArr) {
        discreteEventSystem->enqueueEvent(order->arrivalTime, [&coffeeMode2, order](){coffeeMode2.addOrder(order);});
    }

    while (!discreteEventSystem->isEmpty()) {
        discreteEventSystem->dequeueEvent();
    }

    for(int maxSize : coffeeMode2.getMaxSizeOfBaristaOrderQueue())
        outputStream << maxSize << std::endl;

    for(float cashierBusyTime:coffeeMode2.getCashiersBusyTime())
        outputStream << round(cashierBusyTime/lastEventTime) << std::endl;

    for(float baristaBusyTime:coffeeMode2.getBaristasBusyTime())
        outputStream << round(baristaBusyTime/lastEventTime) << std::endl;

    for(auto order:orderArr)
        outputStream << round(order->completionTime - order->arrivalTime) << std::endl;

    DiscreteEventSystem::deleteInstance();
}

void App::readInput() {

    inputStream >> numberOfCashier ;
    inputStream >> numberOfOrders ;

    while(numberOfOrders--) {
        float arrivalTime;
        inputStream >> arrivalTime;

        float orderTime;
        inputStream >> orderTime;

        float brewTime;
        inputStream >> brewTime;

        float price;
        inputStream >> price;

        auto order = new Order(arrivalTime, orderTime, brewTime, price);
        orderArr.push_back(order);
    }


}

/**
 * converts a float number to a string to write by pricision 2
 * @param number
 * @return string of number
 */
std::string App::round(float number) {

    std::stringstream stream;

    stream.precision(2);
    stream << std::fixed;

    // Convert float to string
    stream<<number;
    std::string str  = stream.str();

    return str;
}

App::~App() {
    for(auto order : orderArr) {
        delete order;
    }
}


