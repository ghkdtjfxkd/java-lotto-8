package lotto.controller;

import lotto.common.BusinessException;

@FunctionalInterface
interface ExecutableTask {
    void execute() throws BusinessException;
}
