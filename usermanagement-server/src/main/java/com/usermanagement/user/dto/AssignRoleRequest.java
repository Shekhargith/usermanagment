package com.usermanagement.user.dto;

import org.antlr.v4.runtime.misc.NotNull;

public record AssignRoleRequest(@NotNull Long roleId, Long assignedBy) {}
