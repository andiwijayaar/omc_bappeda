function check(input) {
    if (input.value != $('#passwordSettingUserModal').val()) {
        input.setCustomValidity('Password Must be Matching.');
    } else {
        input.setCustomValidity('');
    }
}