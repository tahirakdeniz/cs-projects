//
// Created by muham on 4.12.2022.
//

#ifndef ASM203_3_V2_DISCRETEEVENTSYSTEM_H
#define ASM203_3_V2_DISCRETEEVENTSYSTEM_H

#include <functional>
#include <iostream>

/**
 * a singleton special priority queue implementation (linked list implementation) for Discrete Event System.
 */
class DiscreteEventSystem {

    static DiscreteEventSystem* instance;

    struct EventNode {
        EventNode *next = nullptr;
        float time;
        std::function<void()> function;

        EventNode(float time, const std::function<void()> &function);
    };

    EventNode * head = nullptr;
    DiscreteEventSystem()= default;
    float lastEventTime = 0;

public:
    static DiscreteEventSystem* getInstance();
    static void deleteInstance();

    void enqueueEvent(float time, const std::function<void()> &function);
    void dequeueEvent();
    bool isEmpty();
    float getLastEventTime() const;

};

typedef std::function<void()> EVENT;


#endif //ASM203_3_V2_DISCRETEEVENTSYSTEM_H
