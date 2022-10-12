package com.seat.code.domain;

import com.seat.code.domain.enums.Operation;

import java.util.Stack;


public class Instructions {
    private final Stack<Operation> operationStack;
    private final Stack<Operation> operationExecutedStack;
    private int stackPointer;

    public Instructions(Stack<Operation> operationStack) {
        this.operationStack = operationStack;
        this.operationExecutedStack = new Stack<>();
        this.stackPointer = operationStack.size() - 1;
    }

    /**
     * Extract next available instruction
     * Decreases stackPointer *not used for now
     * @return next available operation
     */
    public Operation extractOperation() {
        Operation operation = operationStack.pop();
        operationExecutedStack.push(operation);
        stackPointer--;
        return operation;
    }

    /**
     * True if there is some operation inside Stack
     * @return
     */
    public boolean operationsAvailable() {
        return !operationStack.isEmpty();
    }

    public Operation peakNextOperation() {
        if (operationStack.isEmpty()){
            return null;
        }
        return operationStack.peek();
    }

}
