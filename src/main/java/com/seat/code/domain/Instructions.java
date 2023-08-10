package com.seat.code.domain;

import com.seat.code.domain.enums.Operation;

import java.util.Stack;

/**
 * This class represents a stack of instructions for a mower.
 */
public class Instructions {

    private final Stack<Operation> operationStack;
    private final Stack<Operation> operationExecutedStack;

    public Instructions(Stack<Operation> operationStack) {
        this.operationStack = operationStack;
        this.operationExecutedStack = new Stack<>();
    }

    /**
     * Extracts the next available instruction.
     * Decreases the stackPointer.
     *
     * @return The next available operation.
     */
    public Operation extractOperation() {
        Operation operation = operationStack.pop();
        operationExecutedStack.push(operation);
        return operation;
    }

    /**
     * Checks if there are any operations available in the stack.
     *
     * @return True if there are operations available, false otherwise.
     */
    public boolean operationsAvailable() {
        return !operationStack.isEmpty();
    }

    /**
     * Peeks at the next operation in the stack.
     *
     * @return The next operation or null if the stack is empty.
     */
    public Operation peakNextOperation() {
        if (operationStack.isEmpty()) {
            return null;
        }
        return operationStack.peek();
    }
}
