package com.example.fitnessclub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

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
        boolean isValidUserName = false;
        boolean isValidFirstName = false;
        boolean isValidLastName = false;
        boolean isValidEmail = false;
        boolean isValidPassword = false;
        boolean isValidReenterPassword = false;

        binding.userNameInputText.getText(); //get username
        binding.firstNameInputText.getText();//get first name
        binding.lastNameInputText.getText();//get last name
        binding.emailAddressInputText.getText();  //get email address
        binding.passwordInputText.getText();// get password
        binding.reenterPasswordInputText.getText();// get reenter password


        if (binding.userNameInputText.getText().toString().isEmpty()) {
            binding.userNameInputError.setError(getResources().getString(R.string.userName_empty_message));
            isValidUserName = false;
            //check the user name whether empty
        } else {
            binding.userNameInputError.setErrorEnabled(false);
            //remove error message
            isValidUserName = true;
        }

        if (binding.firstNameInputText.getText().toString().isEmpty()) {
            binding.firstNameInputError.setError(getResources().getString(R.string.firstName_empty_message));
            //check the first name whether empty
            isValidFirstName = false;
        } else {
            binding.firstNameInputError.setErrorEnabled(false);
            //remove error message
            isValidFirstName = true;
        }

        if (binding.lastNameInputText.getText().toString().isEmpty()) {
            binding.lastNameInputError.setError(getResources().getString(R.string.lastName_empty_message));
            //check the last name whether empty
            isValidLastName = false;
        } else {
            binding.lastNameInputError.setErrorEnabled(false);
            //remove error message
            isValidLastName = true;
        }

        if (binding.emailAddressInputText.getText().toString().isEmpty()){
            //check the email address whether empty
            binding.emailAddressInputError.setError(getResources().getString(R.string.email_empty_message));
            isValidEmail = false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailAddressInputText.getText().toString()).matches()) {
            binding.emailAddressInputError.setError(getResources().getString(R.string.email_error_format_message));
            //check the format of email address
            isValidEmail = false;
        } else {binding.emailAddressInputError.setErrorEnabled(false);
            //if address is right, remove the error message
            isValidEmail = true;
        }

        if(binding.passwordInputText.getText().toString().isEmpty()) {
            binding.passwordInputError.setError(getResources().getString(R.string.password_empty_message));
            //check the password whether empty
            isValidPassword = false;
        }
        else {
            binding.passwordInputError.setErrorEnabled(false);
            //remove error message
            isValidPassword = true;
        }

        if(binding.reenterPasswordInputText.getText().toString().isEmpty()){
            binding.reenterPasswordInputError.setError(getResources().getString(R.string.reenter_password_empty_message));
            //check the reenter password whether empty
            isValidReenterPassword = false;
        }
        else if (!binding.passwordInputText.getText().toString().equals(binding.reenterPasswordInputText.getText().toString())){
            binding.reenterPasswordInputError.setError(getResources().getString(R.string.reenter_password_different_message));
            //check the reenter password whether same with the password
            isValidReenterPassword = false;
        }
        else {
            binding.reenterPasswordInputError.setErrorEnabled(false);
            isValidReenterPassword = true;
        }

        if (isValidUserName && isValidFirstName && isValidLastName && isValidEmail && isValidPassword && isValidReenterPassword){

            //if all of the information signup are valid,show success message
            Toast.makeText(SignupActivity.this, "Sign Up Success!", Toast.LENGTH_SHORT).show();
        }
    }

}
