# ThinkFamily - An Internet of Things Project
#### MOTIVATION
We are focused on what is closest to every one of us - Family. In particular, we seek to boost family relations by increasing the amount of interaction between the elderly and their children/grandchildren.

The elderly, especially the widowed and those who live on their own, often experience deafening loneliness. We hope that by connecting the elderly and their loved ones through the implementation of Internet of Things (IoT) devices, we will be seeing more smiles on their faces. Of course, simple and intuitive UI is imperative, as most elderly are not tech-savvy.


#### PROPOSED SOLUTION
We can help the elderly to connect with their families by using photos. Communication is at heart of every connection, and photos are a great conversation starter. Our solution consists of three components:  (1) smart photo frame;  (2) activity tracking wristband;  (3) mobile application


#### APP FEATURES
###### 1. Camera Fragment
Family members can easily snap a photo of themselves, which will then be uploaded to the cloud. The smart photo frame will automatically pull any new photos from the cloud to be displayed for the elderly's viweing pleasure. This is made possible with the Google Drive API which allows us to automatically upload the photos once taken.

###### 2. Data Analytics Fragment
The app will automatically data pertaining to the activity level of the elderly measured using the activity tracker band. The app shows the user concrete figures of data such as calories and number of steps. The app also provides a visual representation of the data across a certain period of time, so that the user is able to compare their loved one's activity level through time. 

The activity band is also configured to be able to pair with any family member’s mobile device whenever the band and the user's smartphone is in close proximity, so as to check how many days have gone by since one have last visited their elderly. If the number of days were to exceed a certain amount, it would advise the user to interact more with the elderly.

Regular notifications will pop up on the family member's mobile phones, reminding them to connect with the elderly by sharing photos of their everyday lives. If the data synced from ThingSpeak indicates a fatal fall or low activity level, a high alert notification will be sent to the mobile phones reminding them to check the elderly and interact with them more often. The Skype button on the toolbar will automatically call the smart photo frame through Skype, so that the users can talk to their loved ones instantaneously.

###### 3. Suggestions Fragment
We provide the users with suggestions on various activities they could initiate and spend time with the elderly. The list of suggestions is presented an elegant card view, with the incorporation of the latest Material Design implementation by Android. Clicking on the "Add to calendar" button on the respective cards will launch the user's calendar app to prompt the user the save the event to their personal calendar. 

Future improvements to this function may include a platform for businesses to populate this fragment with their own events catered for bonding between the elderly and their loved ones. It would be a great medium for businesses to advertise as well as for the users of the app to get fresh ideas on how to engage the older generation.


#### BUILT WITH
Android SDK, Google Services, ThinkSpeak
