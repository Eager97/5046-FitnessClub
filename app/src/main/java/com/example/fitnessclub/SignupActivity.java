package com.example.fitnessclub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessclub.databinding.ActivitySignupscreenBinding;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupscreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInsatanceState) {
        super.onCreate(savedInsatanceState);
        binding = ActivitySignupscreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //back to login screen when user click the back text.
        binding.backLoginScreenTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSignUpValid();//when user click the sign up button, check the information whether valid
            }
        });

    }


    private void checkSignUpValid() {
        binding.userNameInputText.getText(); //get username
        binding.firstNameInputText.getText();//get first name
        binding.lastNameInputText.getText();//get last name
        binding.emailAddressInputText.getText();  //get email address
        binding.passwordInputText.getText();// get password
        binding.reenterPasswordInputText.getText();// get reenter password

        if (binding.userNameInputText.getText().toString().isEmpty()) {
            binding.userNameInputError.setError(getResources().getString(R.string.userName_empty_message));
            //check the user name whether empty
        } else {
            binding.userNameInputError.setErrorEnabled(false);
            //remove error message
        }

        if (binding.firstNameInputText.getText().toString().isEmpty()) {
            binding.firstNameInputError.setError(getResources().getString(R.string.firstName_empty_message));
            //check the first name whether empty
        } else {
            binding.firstNameInputError.setErrorEnabled(false);
            //remove error message
        }

        if (binding.lastNameInputText.getText().toString().isEmpty()) {
            binding.lastNameInputError.setError(getResources().getString(R.string.lastName_empty_message));
            //check the last name whether empty
        } else {
            binding.lastNameInputError.setErrorEnabled(false);
            //remove error message
        }

        if (binding.emailAddressInputText.getText().toString().isEmpty())
        //check the email address whether empty
        {
            binding.emailAddressInputError.setError(getResources().getString(R.string.email_empty_message));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailAddressInputText.getText().toString()).matches()) {
            binding.emailAddressInputError.setError(getResources().getString(R.string.email_error_format_message));
            //check the format of email address
        } else {binding.emailAddressInputError.setErrorEnabled(false);
            //if address is right, remove the error message
        }

        if(binding.passwordInputText.getText().toString().isEmpty()) {
            binding.passwordInputError.setError(getResources().getString(R.string.password_empty_message));
            //check the password whether empty
        }
        else {
            binding.passwordInputError.setErrorEnabled(false);
            //remove error message
        }

        if(binding.reenterPasswordInputText.getText().toString().isEmpty()){
            binding.passwordInputError.setError(getResources().getString(R.string.reenter_password_empty_message));
            //check the reenter password whether empty
        }
        else if (binding.passwordInputText.getText() !=  binding.reenterPasswordInputText.getText()){
            binding.reenterPasswordInputError.setError(getResources().getString(R.string.reenter_password_different_message));
            //check the reenter password whether same with the password
        }
        else {
            binding.reenterPasswordInputError.setErrorEnabled(false);
        }
    }

}
