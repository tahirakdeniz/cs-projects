//
// Created by muham on 4.12.2022.
//

#include "DiscreteEventSystem.h"

DiscreteEventSystem* DiscreteEventSystem::instance = nullptr;;

void DiscreteEventSystem::enqueueEvent(float time, const std::function<void()> &function) {
    auto newEventNode = new EventNode(time, function);

    // if head is null then assign to head
    if(!head){
        head = newEventNode;
        return;
    }

    // if time is smaller than head then make head new node;
    if(time < head->time) {
        newEventNode->next = head;
        head = newEventNode;
        return;
    }


    EventNode * node = head;
    while(node) {
        // if next is null then add new node after the node
        if(!node->next) {
            node->next = newEventNode;
            return;
        }

        // if time is smaller than time of the next node then add new node after the node
        if(time < node->next->time) {
            newEventNode->next = node->next;
            node->next = newEventNode;
            return;
        }

        // if time is bigger or equal to the time of the next node,
        // then continue to traverse next node to add new node after next node.
        node= node->next;
    }
}

void DiscreteEventSystem::dequeueEvent() {
    if(!head) return;

    lastEventTime = head->time;
    head->function();
    EventNode* next = head->next;
    delete head;
    head = next;
}

bool DiscreteEventSystem::isEmpty() {
    return !head;
}

DiscreteEventSystem *DiscreteEventSystem::getInstance() {
    if (!instance)
        instance = new DiscreteEventSystem();
    return instance;
}

float DiscreteEventSystem::getLastEventTime() const {
    return lastEventTime;
}

void DiscreteEventSystem::deleteInstance() {
    delete instance;
    instance = nullptr;
}

DiscreteEventSystem::EventNode::EventNode(float time, const std::function<void()> &function) : time(time),
                                                                                               function(function) {}
