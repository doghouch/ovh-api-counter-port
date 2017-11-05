# ovh-api-counter-port

Port (in Java) for OVH Counter (https://github.com/HarryC145/OVH-API/blob/master/Depression.py)

# What does this do?

It contacts the API to pull the total amount of money that you've spent @ OVH.

# Screenshot(s)

![Screenshot 1](https://i-cdn.dawgy.pw/2fa7ec0b59.png)

# Creating an API Key on OVH

Head to:

- EU Users: [Click here](https://eu.api.ovh.com/createToken/index.cgi?GET=/*&PUT=/*&POST=/*&DELETE=/*)
- CA Users: [Click here](https://ca.api.ovh.com/createToken/index.cgi?GET=/*&PUT=/*&POST=/*&DELETE=/*)
- US Users: [Click here](https://us.api.ovh.com/createToken/index.cgi?GET=/*&PUT=/*&POST=/*&DELETE=/*)

Once you head to the (region-specific) page, you'll need to sign in.

Fill the fields accordingly and enter "OVHCounter" for the "Script Name" field. 

Put "n/a" for the description and select "1 day" for the validity field.

You will see the following once the field is filled out:

![Screenshot 2](https://i-cdn.dawgy.pw/9aa36e3d1b.png)

Click "Create Keys" and you'll see the following:

![Screenshot 3](https://i-cdn.dawgy.pw/7c4f82502b.png)

Copy the details down and paste them into the `config.properties` file.

Enjoy seeing the ungodly amount of money that you've spent at OVH.

