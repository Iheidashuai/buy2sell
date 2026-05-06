package com.buy2sell.domain.task;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TaskTitleTest {

    @Test
    void should_trim_title() {
        assertThat(TaskTitle.of("  hello  ").value()).isEqualTo("hello");
    }

    @Test
    void should_reject_blank_title() {
        assertThatThrownBy(() -> TaskTitle.of("  "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task title must not be blank");
    }
}
