package com.example.samuraitravel.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReviewRegisterForm {
    @NotNull(message = "評価を選択してください。")
    @Min(value = 1, message = "評価は1以上を設定してください。")
    @Max(value = 5, message = "評価は5以下を設定してください。")
    private Integer rating;

    @NotNull(message = "コメントを入力してください。")
    private String comment;
}