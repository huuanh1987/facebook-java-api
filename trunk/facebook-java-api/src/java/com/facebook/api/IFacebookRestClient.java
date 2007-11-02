/*
  +---------------------------------------------------------------------------+
  | Facebook Development Platform Java Client                                 |
  +---------------------------------------------------------------------------+
  | Copyright (c) 2007 Facebook, Inc.                                         |
  | All rights reserved.                                                      |
  |                                                                           |
  | Redistribution and use in source and binary forms, with or without        |
  | modification, are permitted provided that the following conditions        |
  | are met:                                                                  |
  |                                                                           |
  | 1. Redistributions of source code must retain the above copyright         |
  |    notice, this list of conditions and the following disclaimer.          |
  | 2. Redistributions in binary form must reproduce the above copyright      |
  |    notice, this list of conditions and the following disclaimer in the    |
  |    documentation and/or other materials provided with the distribution.   |
  |                                                                           |
  | THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR      |
  | IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES |
  | OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.   |
  | IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,          |
  | INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT  |
  | NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, |
  | DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY     |
  | THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT       |
  | (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF  |
  | THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.         |
  +---------------------------------------------------------------------------+
  | For help with this library, contact developers-help@facebook.com          |
  +---------------------------------------------------------------------------+
*/

package com.facebook.api;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.facebook.api.schema.Listing;

/**
 * Generic interface for a FacebookRestClient, parameterized by output format.
 * For continually updated documentation, please refer to the
 * <a href="http://wiki.developers.facebook.com/index.php/API">
 * Developer Wiki</a>.
 */
public interface IFacebookRestClient<T> {
  public static final String TARGET_API_VERSION = "1.0";
  public static final String ERROR_TAG         = "error_response";
  public static final String FB_SERVER         = "api.facebook.com/restserver.php";
  public static final String SERVER_ADDR       = "http://" + FB_SERVER;
  public static final String HTTPS_SERVER_ADDR = "https://" + FB_SERVER;

  /**
   * Toggle debug mode.
   * 
   * @param isDebug set to true to enable debug
   *                set to false to disable debug
   */
  public void setDebug(boolean isDebug);

  /**
   * Check to see if debug mode is enabled.
   * 
   * @return true if debug is enabled
   *         false otherwise
   */
  public boolean isDebug();

  /**
   * Check to see if the client is running in desktop-app mode
   * 
   * @return true if the app is running in desktop mode.
   *         false otherwise
   */
  public boolean isDesktop();

  /**
   * Set the client to run in desktop-app mode.
   * 
   * @param isDesktop set to true to enable desktop mode
   *                  set to false to disable desktop mode
   */
  public void setIsDesktop(boolean isDesktop);

  /**
   * Sets the FBML for a user's profile, including the content for both the profile box
   * and the profile actions.
   * @param userId - the user whose profile FBML to set
   * @param fbmlMarkup - refer to the FBML documentation for a description of the markup and its role in various contexts
   * @return a boolean indicating whether the FBML was successfully set
   */
  public boolean profile_setFBML(CharSequence fbmlMarkup, Long userId)
    throws FacebookException, IOException;

  /**
   * Gets the FBML for a user's profile, including the content for both the profile box
   * and the profile actions.
   * @param userId - the user whose profile FBML to set
   * @return a T containing FBML markup
   */
  public T profile_getFBML(Long userId)
    throws FacebookException, IOException;

  /**
   * Recaches the referenced url.
   * @param url string representing the URL to refresh
   * @return boolean indicating whether the refresh succeeded
   */
  public boolean fbml_refreshRefUrl(String url)
    throws FacebookException, IOException;

  /**
   * Recaches the referenced url.
   * @param url the URL to refresh
   * @return boolean indicating whether the refresh succeeded
   */
  public boolean fbml_refreshRefUrl(URL url)
    throws FacebookException, IOException;

  /**
   * Recaches the image with the specified imageUrl.
   * @param imageUrl String representing the image URL to refresh
   * @return boolean indicating whether the refresh succeeded
   */
  public boolean fbml_refreshImgSrc(String imageUrl)
    throws FacebookException, IOException;

  /**
   * Recaches the image with the specified imageUrl.
   * @param imageUrl the image URL to refresh
   * @return boolean indicating whether the refresh succeeded
   */
  public boolean fbml_refreshImgSrc(URL imageUrl)
    throws FacebookException, IOException;

  /**
   * Publishes a Mini-Feed story describing an action taken by a user, and
   * publishes aggregating News Feed stories to the friends of that user.
   * Stories are identified as being combinable if they have matching templates and substituted values.
   * @param actorId the user into whose mini-feed the story is being published.
   * @param titleTemplate markup (up to 60 chars, tags excluded) for the feed story's title
   *        section. Must include the token <code>{actor}</code>.
   * @return whether the action story was successfully published; false in case
   *         of a permission error
   * @see <a href="http://wiki.developers.facebook.com/index.php/Feed.publishTemplatizedAction">
   *      Developers Wiki: Feed.publishTemplatizedAction</a>
   */
  public boolean feed_publishTemplatizedAction(Long actorId, CharSequence titleTemplate)
    throws FacebookException, IOException;

  /**
   * Publishes a Mini-Feed story describing an action taken by a user, and
   * publishes aggregating News Feed stories to the friends of that user.
   * Stories are identified as being combinable if they have matching templates and substituted values.
   * @param actorId the user into whose mini-feed the story is being published.
   * @param titleTemplate markup (up to 60 chars, tags excluded) for the feed story's title
   *        section. Must include the token <code>{actor}</code>.
   * @param titleData (optional) contains token-substitution mappings for tokens that appear in
   *        titleTemplate. Should not contain mappings for the <code>{actor}</code> or
   *        <code>{target}</code> tokens. Required if tokens other than <code>{actor}</code>
   *        or <code>{target}</code> appear in the titleTemplate.
   * @param bodyTemplate (optional) markup to be displayed in the feed story's body section.
   *        can include tokens, of the form <code>{token}</code>, to be substituted using
   *        bodyData.
   * @param bodyData (optional) contains token-substitution mappings for tokens that appear in
   *        bodyTemplate. Required if the bodyTemplate contains tokens other than <code>{actor}</code>
   *        and <code>{target}</code>.
   * @param bodyGeneral (optional) additional body markup that is not aggregated. If multiple instances
   *        of this templated story are combined together, the markup in the bodyGeneral of
   *        one of their stories may be displayed.
   * @param targetIds The user ids of friends of the actor, used for stories about a direct action between
   *        the actor and these targets of his/her action. Required if either the titleTemplate or bodyTemplate
   *        includes the token <code>{target}</code>.
   * @param images (optional) additional body markup that is not aggregated. If multiple instances
   *        of this templated story are combined together, the markup in the bodyGeneral of
   *        one of their stories may be displayed.
   * @return whether the action story was successfully published; false in case
   *         of a permission error
   * @see <a href="http://wiki.developers.facebook.com/index.php/Feed.publishTemplatizedAction">
   *      Developers Wiki: Feed.publishTemplatizedAction</a>
   */
  public boolean feed_publishTemplatizedAction(Long actorId, CharSequence titleTemplate,
                                               Map<String,CharSequence> titleData,
                                               CharSequence bodyTemplate,
                                               Map<String,CharSequence> bodyData,
                                               CharSequence bodyGeneral,
                                               Collection<Long> targetIds,
                                               Collection<Pair<URL, URL>> images
                                              )
    throws FacebookException, IOException;

  /**
   * Publish the notification of an action taken by a user to newsfeed.
   * @param title the title of the feed story (up to 60 characters, excluding tags)
   * @param body (optional) the body of the feed story (up to 200 characters, excluding tags)
   * @param images (optional) up to four pairs of image URLs and (possibly null) link URLs
   * @return whether the story was successfully published; false in case of permission error
   * @see <a href="http://wiki.developers.facebook.com/index.php/Feed.publishActionOfUser">
   *      Developers Wiki: Feed.publishActionOfUser</a>
   *      
   * @deprecated Facebook will be removing this API call.  Please use feed_publishTemplatizedAction instead.
   */
  public boolean feed_publishActionOfUser(CharSequence title, CharSequence body,
                                          Collection<Pair<URL, URL>> images)
    throws FacebookException, IOException;

  /**
   * Publish the notification of an action taken by a user to newsfeed.
   * @param title the title of the feed story (up to 60 characters, excluding tags)
   * @param body (optional) the body of the feed story (up to 200 characters, excluding tags)
   * @return whether the story was successfully published; false in case of permission error
   * @see <a href="http://wiki.developers.facebook.com/index.php/Feed.publishActionOfUser">
   *      Developers Wiki: Feed.publishActionOfUser</a>
   *      
   * @deprecated Facebook will be removing this API call.  Please use feed_publishTemplatizedAction instead.
   */
  public boolean feed_publishActionOfUser(CharSequence title, CharSequence body)
    throws FacebookException, IOException;

  /**
   * Publish a story to the logged-in user's newsfeed.
   * @param title the title of the feed story
   * @param body the body of the feed story
   * @param images (optional) up to four pairs of image URLs and (possibly null) link URLs
   * @param priority
   * @return whether the story was successfully published; false in case of permission error
   * @see <a href="http://wiki.developers.facebook.com/index.php/Feed.publishStoryToUser">
   *      Developers Wiki: Feed.publishStoryToUser</a>
   */
  public boolean feed_publishStoryToUser(CharSequence title, CharSequence body,
                                         Collection<Pair<URL, URL>> images, Integer priority)
    throws FacebookException, IOException;

  /**
   * Publish a story to the logged-in user's newsfeed.
   * @param title the title of the feed story
   * @param body the body of the feed story
   * @return whether the story was successfully published; false in case of permission error
   * @see <a href="http://wiki.developers.facebook.com/index.php/Feed.publishStoryToUser">
   *      Developers Wiki: Feed.publishStoryToUser</a>
   */
  public boolean feed_publishStoryToUser(CharSequence title, CharSequence body)
    throws FacebookException, IOException;

  /**
   * Publish a story to the logged-in user's newsfeed.
   * @param title the title of the feed story
   * @param body the body of the feed story
   * @param priority
   * @return whether the story was successfully published; false in case of permission error
   * @see <a href="http://wiki.developers.facebook.com/index.php/Feed.publishStoryToUser">
   *      Developers Wiki: Feed.publishStoryToUser</a>
   */
  public boolean feed_publishStoryToUser(CharSequence title, CharSequence body, Integer priority)
    throws FacebookException, IOException;

  /**
   * Publish a story to the logged-in user's newsfeed.
   * @param title the title of the feed story
   * @param body the body of the feed story
   * @param images (optional) up to four pairs of image URLs and (possibly null) link URLs
   * @return whether the story was successfully published; false in case of permission error
   * @see <a href="http://wiki.developers.facebook.com/index.php/Feed.publishStoryToUser">
   *      Developers Wiki: Feed.publishStoryToUser</a>
   */
  public boolean feed_publishStoryToUser(CharSequence title, CharSequence body,
                                         Collection<Pair<URL, URL>> images)
    throws FacebookException, IOException;

  /**
   * Returns all visible events according to the filters specified. This may be used to find all events of a user, or to query specific eids.
   * @param eventIds filter by these event ID's (optional)
   * @param userId filter by this user only (optional)
   * @param startTime UTC lower bound (optional)
   * @param endTime UTC upper bound (optional)
   * @return T of events
   */
  public T events_get(Long userId, Collection<Long> eventIds, Long startTime, Long endTime)
    throws FacebookException, IOException;

  /**
   * Retrieves the membership list of an event
   * @param eventId event id
   * @return T consisting of four membership lists corresponding to RSVP status, with keys
   * 'attending', 'unsure', 'declined', and 'not_replied'
   */
  public T events_getMembers(Number eventId)
    throws FacebookException, IOException;

  /**
   * Retrieves whether two users are friends.
   * @param userId1
   * @param userId2
   * @return T
   * @see <a href="http://wiki.developers.facebook.com/index.php/Friends.areFriends">
   *      Developers Wiki: Friends.areFriends</a>
   */
  public T friends_areFriends(long userId1, long userId2)
    throws FacebookException, IOException;

  /**
   * Retrieves whether pairs of users are friends.
   * Returns whether the first user in <code>userIds1</code> is friends with the first user in
   * <code>userIds2</code>, the second user in <code>userIds1</code> is friends with the second user in
   * <code>userIds2</code>, etc.
   * @param userIds1
   * @param userIds2
   * @return T
   * @see <a href="http://wiki.developers.facebook.com/index.php/Friends.areFriends">
   *      Developers Wiki: Friends.areFriends</a>
   */
  public T friends_areFriends(Collection<Long> userIds1, Collection<Long> userIds2)
    throws FacebookException, IOException;

  /**
   * Retrieves the friends of the currently logged in user.
   * @return array of friends
   */
  public T friends_get()
    throws FacebookException, IOException;

  /**
   * Retrieves the friends of the currently logged in user, who are also users
   * of the calling application.
   * @return array of friends
   */
  public T friends_getAppUsers()
    throws FacebookException, IOException;

  /**
   * Retrieves the requested info fields for the requested set of users.
   * @param userIds a collection of user IDs for which to fetch info
   * @param fields a set of ProfileFields
   * @return a T consisting of a list of users, with each user element
   * containing the requested fields.
   */
  public T users_getInfo(Collection<Long> userIds, EnumSet<ProfileField> fields)
    throws FacebookException, IOException;

  /**
   * Retrieves the requested info fields for the requested set of users.
   * @param userIds a collection of user IDs for which to fetch info
   * @param fields a set of strings describing the info fields desired, such as "last_name", "sex"
   * @return a T consisting of a list of users, with each user element
   * containing the requested fields.
   */
  public T users_getInfo(Collection<Long> userIds, Set<CharSequence> fields)
    throws FacebookException, IOException;

  /**
   * Retrieves the user ID of the user logged in to this API session
   * @return the Facebook user ID of the logged-in user
   */
  public long users_getLoggedInUser()
    throws FacebookException, IOException;

  /**
   * Retrieves an indicator of whether the logged-in user has added the
   * application associated with the _apiKey.
   * @return boolean indicating whether the user has added the app
   * @see <a href="http://wiki.developers.facebook.com/index.php/Users.isAppAdded">
   *      Developers Wiki: Users.isAppAdded</a>
   */
  public boolean users_isAppAdded()
    throws FacebookException, IOException;

  /**
   * Retrieves whether the logged-in user has granted the specified permission to this application.
   * @param permission an extended permission (e.g. FacebookExtendedPerm.MARKETPLACE,
   *        "photo_upload")
   * @return boolean indicating whether the user has the permission
   * @see FacebookExtendedPerm
   * @see <a href="http://wiki.developers.facebook.com/index.php/Users.hasAppPermission">
   *      Developers Wiki: Users.hasAppPermission</a>
   *      
   * @deprecated provided for legacy support only.  Please use the alternate version.
   */
  public boolean users_hasAppPermission(CharSequence permission)
    throws FacebookException, IOException;

  /**
   * Sets the logged-in user's Facebook status.
   * Requires the status_update extended permission.
   * @return whether the status was successfully set
   * @see #users_hasAppPermission
   * @see FacebookExtendedPerm#STATUS_UPDATE
   * @see <a href="http://wiki.developers.facebook.com/index.php/Users.setStatus">
   *      Developers Wiki: Users.setStatus</a>
   */
  public boolean users_setStatus(String status)
    throws FacebookException, IOException;
  
  /**
   * Set the user's profile status message.  This requires that the user has granted the application the
   * 'status_update' permission, otherwise the call will return an error.  You can use 'users_hasAppPermission'
   * to check to see if the user has granted your app the abbility to update their status.
   *
   * @param newStatus the new status message to set.
   * @param clear whether or not to clear the old status message.
   *
   * @return true if the call succeeds
   *         false otherwise
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public boolean users_setStatus(String newStatus, boolean clear) throws FacebookException, IOException;

  /**
   * Clears the logged-in user's Facebook status.
   * Requires the status_update extended permission.
   * @return whether the status was successfully cleared
   * @see #users_hasAppPermission
   * @see FacebookExtendedPerm#STATUS_UPDATE
   * @see <a href="http://wiki.developers.facebook.com/index.php/Users.setStatus">
   *      Developers Wiki: Users.setStatus</a>
   */
  public boolean users_clearStatus()
    throws FacebookException, IOException;

  /**
   * Used to retrieve photo objects using the search parameters (one or more of the
   * parameters must be provided).
   *
   * @param subjId retrieve from photos associated with this user (optional).
   * @param albumId retrieve from photos from this album (optional)
   * @param photoIds retrieve from this list of photos (optional)
   *
   * @return an T of photo objects.
   */
  public T photos_get(Long subjId, Long albumId, Collection<Long> photoIds)
    throws FacebookException, IOException;

  /**
   * Used to retrieve photo objects using the search parameters (one or more of the
   * parameters must be provided).
   *
   * @param subjId retrieve from photos associated with this user (optional).
   * @param photoIds retrieve from this list of photos (optional)
   * @return an T of photo objects.
   * @see #photos_get(Long, Long, Collection)
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.get">
   *      Developers Wiki: Photos.get</a>
   */
  public T photos_get(Long subjId, Collection<Long> photoIds)
    throws FacebookException, IOException;

  /**
   * Used to retrieve photo objects using the search parameters (one or more of the
   * parameters must be provided).
   *
   * @param subjId retrieve from photos associated with this user (optional).
   * @param albumId retrieve from photos from this album (optional)
   * @return an T of photo objects.
   * @see #photos_get(Long, Long, Collection)
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.get">
   *      Developers Wiki: Photos.get</a>
   */
  public T photos_get(Long subjId, Long albumId)
    throws FacebookException, IOException;

  /**
   * Used to retrieve photo objects using the search parameters (one or more of the
   * parameters must be provided).
   *
   * @param photoIds retrieve from this list of photos (optional)
   * @return an T of photo objects.
   * @see #photos_get(Long, Long, Collection)
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.get">
   *      Developers Wiki: Photos.get</a>
   */
  public T photos_get(Collection<Long> photoIds)
    throws FacebookException, IOException;

  /**
   * Used to retrieve photo objects using the search parameters (one or more of the
   * parameters must be provided).
   *
   * @param subjId retrieve from photos associated with this user (optional).
   * @return an T of photo objects.
   * @see #photos_get(Long, Long, Collection)
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.get">
   *      Developers Wiki: Photos.get</a>
   */
  public T photos_get(Long subjId)
    throws FacebookException, IOException;

  /**
   * Retrieves album metadata. Pass a user id and/or a list of album ids to specify the albums
   * to be retrieved (at least one must be provided)
   *
   * @param userId   (optional) the id of the albums' owner (optional)
   * @param albumIds (optional) the ids of albums whose metadata is to be retrieved
   * @return album objects
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.getAlbums">
   *      Developers Wiki: Photos.getAlbums</a>
   */
  public T photos_getAlbums(Long userId, Collection<Long> albumIds)
    throws FacebookException, IOException;

  /**
   * Retrieves album metadata for albums owned by a user.
   * @param userId   (optional) the id of the albums' owner (optional)
   * @return album objects
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.getAlbums">
   *      Developers Wiki: Photos.getAlbums</a>
   */
  public T photos_getAlbums(Long userId)
    throws FacebookException, IOException;

  /**
   * Retrieves album metadata for a list of album IDs.
   * @param albumIds the ids of albums whose metadata is to be retrieved
   * @return album objects
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.getAlbums">
   *      Developers Wiki: Photos.getAlbums</a>
   */
  public T photos_getAlbums(Collection<Long> albumIds)
    throws FacebookException, IOException;

  /**
   * Retrieves the tags for the given set of photos.
   * @param photoIds The list of photos from which to extract photo tags.
   * @return the created album
   */
  public T photos_getTags(Collection<Long> photoIds)
    throws FacebookException, IOException;

  /**
   * Creates an album.
   * @param albumName The list of photos from which to extract photo tags.
   * @return the created album
   */
  public T photos_createAlbum(String albumName)
    throws FacebookException, IOException;

  /**
   * Creates an album.
   * @param name The album name.
   * @param location The album location (optional).
   * @param description The album description (optional).
   * @return an array of photo objects.
   */
  public T photos_createAlbum(String name, String description, String location)
    throws FacebookException, IOException;

  /**
   * Adds several tags to a photo.
   * @param photoId The photo id of the photo to be tagged.
   * @param tags A list of PhotoTags.
   * @return a list of booleans indicating whether the tag was successfully added.
   */
  public T photos_addTags(Long photoId, Collection<PhotoTag> tags)
    throws FacebookException, IOException;

  /**
   * Adds a tag to a photo.
   * @param photoId The photo id of the photo to be tagged.
   * @param xPct The horizontal position of the tag, as a percentage from 0 to 100, from the left of the photo.
   * @param yPct The vertical position of the tag, as a percentage from 0 to 100, from the top of the photo.
   * @param taggedUserId The list of photos from which to extract photo tags.
   * @return whether the tag was successfully added.
   */
  public boolean photos_addTag(Long photoId, Long taggedUserId, Double xPct, Double yPct)
    throws FacebookException, IOException;

  /**
   * Adds a tag to a photo.
   * @param photoId The photo id of the photo to be tagged.
   * @param xPct The horizontal position of the tag, as a percentage from 0 to 100, from the left of the photo.
   * @param yPct The list of photos from which to extract photo tags.
   * @param tagText The text of the tag.
   * @return whether the tag was successfully added.
   */
  public boolean photos_addTag(Long photoId, CharSequence tagText, Double xPct, Double yPct)
    throws FacebookException, IOException;

  /**
   * Uploads a photo to Facebook.
   * @param photo an image file
   * @return a T with the standard Facebook photo information
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.upload">
   * Developers wiki: Photos.upload</a>
   */
  public T photos_upload(File photo)
    throws FacebookException, IOException;

  /**
   * Uploads a photo to Facebook.
   * @param photo an image file
   * @param caption a description of the image contents
   * @return a T with the standard Facebook photo information
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.upload">
   * Developers wiki: Photos.upload</a>
   */
  public T photos_upload(File photo, String caption)
    throws FacebookException, IOException;

  /**
   * Uploads a photo to Facebook.
   * @param photo an image file
   * @param albumId the album into which the photo should be uploaded
   * @return a T with the standard Facebook photo information
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.upload">
   * Developers wiki: Photos.upload</a>
   */
  public T photos_upload(File photo, Long albumId)
    throws FacebookException, IOException;

  /**
   * Uploads a photo to Facebook.
   * @param photo an image file
   * @param caption a description of the image contents
   * @param albumId the album into which the photo should be uploaded
   * @return a T with the standard Facebook photo information
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.upload">
   * Developers wiki: Photos.upload</a>
   */
  public T photos_upload(File photo, String caption, Long albumId)
    throws FacebookException, IOException;

  /**
   * Retrieves the groups associated with a user
   * @param userId Optional: User associated with groups.
   * A null parameter will default to the session user.
   * @param groupIds Optional: group ids to query.
   * A null parameter will get all groups for the user.
   * @return array of groups
   */
  public T groups_get(Long userId, Collection<Long> groupIds)
    throws FacebookException, IOException;

  /**
   * Retrieves the membership list of a group
   * @param groupId the group id
   * @return a T containing four membership lists of
   * 'members', 'admins', 'officers', and 'not_replied'
   */
  public T groups_getMembers(Number groupId)
    throws FacebookException, IOException;

  /**
   * Retrieves the results of a Facebook Query Language query
   * @param query : the FQL query statement
   * @return varies depending on the FQL query
   */
  public T fql_query(CharSequence query)
    throws FacebookException, IOException;

  /**
   * Retrieves the outstanding notifications for the session user.
   * @return a T containing
   * notification count pairs for 'messages', 'pokes' and 'shares',
   * a uid list of 'friend_requests', a gid list of 'group_invites',
   * and an eid list of 'event_invites'
   */
  public T notifications_get()
    throws FacebookException, IOException;

  /**
   * Send a notification message to the specified users.
   * @param recipientIds the user ids to which the message is to be sent
   * @param notification the FBML to display on the notifications page
   * @param email the FBML to send to the specified users via email, or null
   *        if no email should be sent
   * @return a URL, possibly null, to which the user should be redirected to finalize
   * the sending of the email
   */
  public URL notifications_send(Collection<Long> recipientIds, CharSequence notification,
                                CharSequence email)
    throws FacebookException, IOException;

  /**
   * Call this function and store the result, using it to generate the
   * appropriate login url and then to retrieve the session information.
   * @return an authentication token
   */
  public String auth_createToken()
    throws FacebookException, IOException;

  /**
   * Call this function to retrieve the session information after your user has
   * logged in.
   * @param authToken the token returned by auth_createToken or passed back to your callback_url.
   */
  public String auth_getSession(String authToken)
    throws FacebookException, IOException;

  /**
   * Call this function to get the user ID.
   *
   * @return The ID of the current session's user, or -1 if none.
   */
  public long auth_getUserId(String authToken)
    throws FacebookException, IOException;

  /**
   * Create a marketplace listing. The create_listing extended permission is required.
   * @param showOnProfile whether
   * @return the id of the created listing
   * @see #users_hasAppPermission
   * @see FacebookExtendedPerm#MARKETPLACE
   * @see <a href="http://wiki.developers.facebook.com/index.php/Marketplace.createListing">
   *      Developers Wiki: marketplace.createListing</a>
   *      
   * @deprecated provided for legacy support only.  Please use the version that takes a MarketListing instead.
   */
  public Long marketplace_createListing(Boolean showOnProfile, MarketplaceListing attrs)
    throws FacebookException, IOException;

  /**
   * Modify a marketplace listing. The create_listing extended permission is required.
   * @return the id of the edited listing
   * @see <a href="http://wiki.developers.facebook.com/index.php/Marketplace.createListing">
   *      Developers Wiki: marketplace.createListing</a>
   *      
   * @deprecated provided for legacy support only.  Please use the version that takes a MarketListing instead.
   */
  public Long marketplace_editListing(Long listingId, Boolean showOnProfile, MarketplaceListing attrs)
    throws FacebookException, IOException;

  /**
   * Remove a marketplace listing. The create_listing extended permission is required.
   * @param listingId the listing to be removed
   * @return boolean indicating whether the listing was removed
   * @see #users_hasAppPermission
   * @see FacebookExtendedPerm#MARKETPLACE
   * @see <a href="http://wiki.developers.facebook.com/index.php/Marketplace.removeListing">
   *      Developers Wiki: marketplace.removeListing</a>
   */
  public boolean marketplace_removeListing(Long listingId)
    throws FacebookException, IOException;

  /**
   * Remove a marketplace listing. The create_listing extended permission is required.
   * @param listingId the listing to be removed
   * @param status MARKETPLACE_STATUS_DEFAULT, MARKETPLACE_STATUS_SUCCESS, or MARKETPLACE_STATUS_NOT_SUCCESS
   * @return boolean indicating whether the listing was removed
   * @see #users_hasAppPermission
   * @see FacebookExtendedPerm#MARKETPLACE
   * @see <a href="http://wiki.developers.facebook.com/index.php/Marketplace.removeListing">
   *      Developers Wiki: marketplace.removeListing</a>
   *      
   * @deprecated provided for legacy support only.  Please use the version that takes a MarketListingStatus instead.
   */
  public boolean marketplace_removeListing(Long listingId, CharSequence status)
    throws FacebookException, IOException;

  /**
   * Get the categories available in marketplace.
   * @return a T listing the marketplace categories
   * @see <a href="http://wiki.developers.facebook.com/index.php/Marketplace.getCategories">
   *      Developers Wiki: marketplace.getCategories</a>
   */
  public List<String> marketplace_getCategories()
    throws FacebookException, IOException;

  /**
   * Get the subcategories available for a category.
   * @param category a category, e.g. "HOUSING"
   * @return a T listing the marketplace sub-categories
   * @see <a href="http://wiki.developers.facebook.com/index.php/Marketplace.getSubCategories">
   *      Developers Wiki: marketplace.getSubCategories</a>
   */
  public T marketplace_getSubCategories(CharSequence category)
    throws FacebookException, IOException;

  /**
   * Fetch marketplace listings, filtered by listing IDs and/or the posting users' IDs.
   * @param listingIds listing identifiers (required if uids is null/empty)
   * @param userIds posting user identifiers (required if listingIds is null/empty)
   * @return a T of marketplace listings
   * @see <a href="http://wiki.developers.facebook.com/index.php/Marketplace.getListings">
   *      Developers Wiki: marketplace.getListings</a>
   */
  public T marketplace_getListings(Collection<Long> listingIds, Collection<Long> userIds)
    throws FacebookException, IOException;

  /**
   * Search for marketplace listings, optionally by category, subcategory, and/or query string.
   * @param category the category of listings desired (optional except if subcategory is provided)
   * @param subCategory the subcategory of listings desired (optional)
   * @param query a query string (optional)
   * @return a T of marketplace listings
   * @see <a href="http://wiki.developers.facebook.com/index.php/Marketplace.search">
   *      Developers Wiki: marketplace.search</a>
   *      
   * @deprecated provided for legacy support only.  Please use the alternate version instead.
   */
  public T marketplace_search(CharSequence category, CharSequence subCategory, CharSequence query)
    throws FacebookException, IOException;
  
  /**
   * Used to retrieve photo objects using the search parameters (one or more of the
   * parameters must be provided).
   *
   * @param albumId retrieve from photos from this album (optional)
   * @param photoIds retrieve from this list of photos (optional)
   * @return an T of photo objects.
   * @see #photos_get(Integer, Long, Collection)
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.get">
   *      Developers Wiki: Photos.get</a>
   */
  public T photos_getByAlbum(Long albumId, Collection<Long> photoIds)
    throws FacebookException, IOException;
  
  /**
   * Used to retrieve photo objects using the search parameters (one or more of the
   * parameters must be provided).
   *
   * @param albumId retrieve from photos from this album (optional)
   * @return an T of photo objects.
   * @see #photos_get(Integer, Long, Collection)
   * @see <a href="http://wiki.developers.facebook.com/index.php/Photos.get">
   *      Developers Wiki: Photos.get</a>
   */
  public T photos_getByAlbum(Long albumId)
    throws FacebookException, IOException;
  
  /**
   * Get the categories available in marketplace.
   * @return a T listing the marketplace categories
   * @see <a href="http://wiki.developers.facebook.com/index.php/Marketplace.getCategories">
   *      Developers Wiki: marketplace.getCategories</a>
   *      
   * @deprecated use the version that returns a List<String> instead.
   */
  public T marketplace_getCategoriesObject()
    throws FacebookException, IOException;
  
  /**
   * Returns a string representation for the last API response recieved from Facebook, exactly as sent by the API server.
   *
   * Note that calling this method consumes the data held in the internal buffer, and thus it may only be called once per API
   * call.
   *
   * @return a String representation of the last API response sent by Facebook
   */
  public String getRawResponse();
  
  /**
   * Returns a JAXB object of the type that corresponds to the last API call made on the client.  Each
   * Facebook Platform API call that returns a Document object has a JAXB response object associated
   * with it.  The naming convention is generally intuitive.  For example, if you invoke the
   * 'user_getInfo' API call, the associated JAXB response object is 'UsersGetInfoResponse'.<br />
   * <br />
   * An example of how to use this method:<br />
   *  <br />
   *    FacebookRestClient client = new FacebookRestClient("apiKey", "secretKey", "sessionId");<br />
   *    client.friends_get();<br />
   *    FriendsGetResponse response = (FriendsGetResponse)client.getResponsePOJO();<br />
   *    List<Long> friends = response.getUid(); <br />
   * <br />
   * This is particularly useful in the case of API calls that return a Document object, as working
   * with the JAXB response object is generally much simple than trying to walk/parse the DOM by
   * hand.<br />
   * <br />
   * This method can be safely called multiple times, though note that it will only return the
   * response-object corresponding to the most recent Facebook Platform API call made.<br />
   * <br />
   * Note that you must cast the return value of this method to the correct type in order to do anything
   * useful with it.
   *
   * @return a JAXB POJO ("Plain Old Java Object") of the type that corresponds to the last API call made on
   *         the client.  Note that you must cast this object to its proper type before you will be able to
   *         do anything useful with it.
   */
  public Object getResponsePOJO();
  
  /**
   * Publishes a templatized action for the current user.  The action will appear in their minifeed,
   * and may appear in their friends' newsfeeds depending upon a number of different factors.  When
   * a template match exists between multiple distinct users (like "Bob recommends Bizou" and "Sally
   * recommends Bizou"), the feed entries may be combined in the newfeed (to something like "Bob and sally
   * recommend Bizou").  This happens automatically, and *only* if the template match between the two
   * feed entries is identical.<br />
   * <br />
   * Feed entries are not aggregated for a single user (so "Bob recommends Bizou" and "Bob recommends Le
   * Charm" *will not* become "Bob recommends Bizou and Le Charm").<br />
   * <br />
   * If the user's action involves one or more of their friends, list them in the 'targetIds' parameter.
   * For example, if you have "Bob says hi to Sally and Susie", and Sally's UID is 1, and Susie's UID is 2,
   * then pass a 'targetIds' paramters of "1,2".  If you pass this parameter, you can use the "{target}" token
   * in your templates.  Probably it also makes it more likely that Sally and Susie will see the feed entry
   * in their newsfeed, relative to any other friends Bob might have.  It may be a good idea to always send
   * a list of all the user's friends, and avoid using the "{target}" token, to maximize distribution of the
   * story through the newsfeed.<br />
   * <br />
   * The only strictly required parameter is 'titleTemplate', which must contain the "{actor}" token somewhere
   * inside of it.  All other parameters, options, and tokens are optional, and my be set to null if
   * being omitted.<br />
   * <br />
   * Not that stories will only be aggregated if *all* templates match and *all* template parameters match, so
   * if two entries have the same templateTitle and titleData, but a different bodyTemplate, they will not
   * aggregate.  Probably it's better to use bodyGeneral instead of bodyTemplate, for the extra flexibility
   * it provides.<br />
   * <br />
   * <br />
   * Note that this method is replacing 'feed_publishActionOfUser', which has been deprecated by Facebook.
   * For specific details, visit http://wiki.developers.facebook.com/index.php/Feed.publishTemplatizedAction
   *
   *
   * @param action a TemplatizedAction instance that represents the feed data to publish
   *
   * @return a Document representing the XML response returned from the Facebook API server.
   *
   * @throws FacebookException if any number of bad things happen
   * @throws IOException
   */
  public boolean feed_PublishTemplatizedAction(TemplatizedAction action) throws FacebookException, IOException; 
  
  /**
   * Lookup a single preference value for the current user.
   *
   * @param prefId the id of the preference to lookup.  This should be an integer value from 0-200.
   *
   * @return The value of that preference, or null if it is not yet set.
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public String data_getUserPreference(Integer prefId) throws FacebookException, IOException;
  
  /**
   * Get a map containing all preference values set for the current user.
   *
   * @return a map of preference values, keyed by preference id.  The map will contain all
   *         preferences that have been set for the current user.  If there are no preferences
   *         currently set, the map will be empty.  The map returned will never be null.
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public Map<Integer, String> data_getUserPreferences() throws FacebookException, IOException;
  
  /**
   * Set a user-preference value.  The value can be any string up to 127 characters in length,
   * while the preference id can only be an integer between 0 and 200.  Any preference set applies
   * only to the current user of the application.
   *
   * To clear a user-preference, specify null as the value parameter.  The values of "0" and "" will
   * be stored as user-preferences with a literal value of "0" and "" respectively.
   *
   * @param prefId the id of the preference to set, an integer between 0 and 200.
   * @param value the value to store, a String of up to 127 characters in length.
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public void data_setUserPreference(Integer prefId, String value) throws FacebookException, IOException;
  
  /**
   * Set multiple user-preferences values.  The values can be strings up to 127 characters in length,
   * while the preference id can only be an integer between 0 and 200.  Any preferences set apply
   * only to the current user of the application.
   *
   * To clear a user-preference, specify null as its value in the map.  The values of "0" and "" will
   * be stored as user-preferences with a literal value of "0" and "" respectively.
   *
   * @param value the values to store, specified in a map. The keys should be preference-id values from 0-200, and
   *              the values should be strings of up to 127 characters in length.
   * @param replace set to true if you want to remove any pre-existing preferences before writing the new ones
   *                set to false if you want the new preferences to be merged with any pre-existing preferences
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public void data_setUserPreferences(Map<Integer, String> values, boolean replace) throws FacebookException, IOException;
  
  /**
   * Check to see if the application is permitted to send SMS messages to the current application user.
   *
   * @return true if the application is presently able to send SMS messages to the current user
   *         false otherwise
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public boolean sms_canSend() throws FacebookException, IOException;
  
  /**
   * Check to see if the application is permitted to send SMS messages to the specified user.
   *
   * @param userId the UID of the user to check permissions for
   *
   * @return true if the application is presently able to send SMS messages to the specified user
   *         false otherwise
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public boolean sms_canSend(Long userId) throws FacebookException, IOException;
  
  /**
   * Send an SMS message to the current application user.
   *
   * @param message the message to send.
   * @param smsSessionId the SMS session id to use, note that that is distinct from the user's facebook session id.  It is used to
   *                     allow applications to keep track of individual SMS conversations/threads for a single user.  Specify
   *                     null if you do not want/need to use a session for the current message.
   * @param makeNewSession set to true to request that Facebook allocate a new SMS session id for this message.  The allocated
   *                       id will be returned as the result of this API call.  You should only set this to true if you are
   *                       passing a null 'smsSessionId' value.  Otherwise you already have a SMS session id, and do not need a new one.
   *
   * @return an integer specifying the value of the session id alocated by Facebook, if one was requested.  If a new session id was
   *                    not requested, this method will return null.
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public Integer sms_send(String message, Integer smsSessionId, boolean makeNewSession) throws FacebookException, IOException;
  
  /**
   * Send an SMS message to the specified user.
   *
   * @param userId the id of the user to send the message to.
   * @param message the message to send.
   * @param smsSessionId the SMS session id to use, note that that is distinct from the user's facebook session id.  It is used to
   *                     allow applications to keep track of individual SMS conversations/threads for a single user.  Specify
   *                     null if you do not want/need to use a session for the current message.
   * @param makeNewSession set to true to request that Facebook allocate a new SMS session id for this message.  The allocated
   *                       id will be returned as the result of this API call.  You should only set this to true if you are
   *                       passing a null 'smsSessionId' value.  Otherwise you already have a SMS session id, and do not need a new one.
   *
   * @return an integer specifying the value of the session id alocated by Facebook, if one was requested.  If a new session id was
   *                    not requested, this method will return null.
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public Integer sms_send(Long userId, String message, Integer smsSessionId, boolean makeNewSession) throws FacebookException, IOException;
  
  /**
   * Check to see if the user has granted the app a specific external permission.  In order to be granted a
   * permission, an application must direct the user to a URL of the form:
   *
   * http://www.facebook.com/authorize.php?api_key=[YOUR_API_KEY]&v=1.0&ext_perm=[PERMISSION NAME]
   *
   * @param perm the permission to check for
   *
   * @return true if the user has granted the application the specified permission
   *         false otherwise
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public boolean users_hasAppPermission(Permission perm) throws FacebookException, IOException;
  
  /**
   * Publishes a templatized action for the current user.  The action will appear in their minifeed,
   * and may appear in their friends' newsfeeds depending upon a number of different factors.  When
   * a template match exists between multiple distinct users (like "Bob recommends Bizou" and "Sally
   * recommends Bizou"), the feed entries may be combined in the newfeed (to something like "Bob and sally
   * recommend Bizou").  This happens automatically, and *only* if the template match between the two
   * feed entries is identical.<br />
   * <br />
   * Feed entries are not aggregated for a single user (so "Bob recommends Bizou" and "Bob recommends Le
   * Charm" *will not* become "Bob recommends Bizou and Le Charm").<br />
   * <br />
   * If the user's action involves one or more of their friends, list them in the 'targetIds' parameter.
   * For example, if you have "Bob says hi to Sally and Susie", and Sally's UID is 1, and Susie's UID is 2,
   * then pass a 'targetIds' paramters of "1,2".  If you pass this parameter, you can use the "{target}" token
   * in your templates.  Probably it also makes it more likely that Sally and Susie will see the feed entry
   * in their newsfeed, relative to any other friends Bob might have.  It may be a good idea to always send
   * a list of all the user's friends, and avoid using the "{target}" token, to maximize distribution of the
   * story through the newsfeed.<br />
   * <br />
   * The only strictly required parameter is 'titleTemplate', which must contain the "{actor}" token somewhere
   * inside of it.  All other parameters, options, and tokens are optional, and my be set to null if
   * being omitted.<br />
   * <br />
   * Not that stories will only be aggregated if *all* templates match and *all* template parameters match, so
   * if two entries have the same templateTitle and titleData, but a different bodyTemplate, they will not
   * aggregate.  Probably it's better to use bodyGeneral instead of bodyTemplate, for the extra flexibility
   * it provides.<br />
   * <br />
   * <br />
   * Note that this method is replacing 'feed_publishActionOfUser', which has been deprecated by Facebook.
   * For specific details, visit http://wiki.developers.facebook.com/index.php/Feed.publishTemplatizedAction
   *
   *
   * @param titleTemplate the template for the title of the feed entry, this must contain the "(actor}" token.
   *                      Any other tokens are optional, i.e. "{actor} recommends {place}".
   * @param titleData JSON-formatted values for any tokens used in titleTemplate, with the exception of "{actor}"
   *                  and "{target}", which Facebook populates automatically, i.e. "{place: "<a href='http://www.bizou.com'>Bizou</a>"}".
   * @param bodyTemplate the template for the body of the feed entry, works the same as 'titleTemplate', but
   *                     is not required to contain the "{actor}" token.
   * @param bodyData works the same as titleData
   * @param bodyGeneral non-templatized content for the body, may contain markup, may not contain tokens.
   * @param pictures a list of up to 4 images to display, with optional hyperlinks for each one.
   * @param targetIds a comma-seperated list of the UID's of any friend(s) who are involved in this feed
   *                  action (if there are any), this specifies the value of the "{target}" token.  If you
   *                  use this token in any of your templates, you must specify a value for this parameter.
   *
   * @return a Document representing the XML response returned from the Facebook API server.
   *
   * @throws FacebookException if any number of bad things happen
   * @throws IOException
   */
  public boolean feed_publishTemplatizedAction(String titleTemplate, String titleData, String bodyTemplate,
          String bodyData, String bodyGeneral, Collection<Pair<URL, URL>> pictures, String targetIds) throws FacebookException, IOException;
  
  /**
   * Associates the specified FBML markup with the specified handle/id.  The markup can then be referenced using the fb:ref FBML
   * tag, to allow a given snippet to be reused easily across multiple users, and also to allow the application to update
   * the fbml for multiple users more easily without having to make a seperate call for each user, by just changing the FBML
   * markup that is associated with the handle/id.
   *
   * @param handle the id to associate the specified markup with.  Put this in fb:ref FBML tags to reference your markup.
   * @param markup the FBML markup to store.
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public void fbml_setRefHandle(String handle, String markup) throws FacebookException, IOException;
  
  /**
   * Create a new marketplace listing, or modify an existing one.
   *
   * @param listingId the id of the listing to modify, set to 0 (or null) to create a new listing.
   * @param showOnProfile set to true to show the listing on the user's profile (Facebook appears to ignore this setting).
   * @param attributes JSON-encoded attributes for this listing.
   *
   * @return the id of the listing created (or modified).
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public Long marketplace_createListing(Long listingId, boolean showOnProfile, String attributes) throws FacebookException, IOException;
  
  /**
   * Create a new marketplace listing, or modify an existing one.
   *
   * @param listingId the id of the listing to modify, set to 0 (or null) to create a new listing.
   * @param showOnProfile set to true to show the listing on the user's profile, set to false to prevent the listing from being shown on the profile.
   * @param listing the listing to publish.
   *
   * @return the id of the listing created (or modified).
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public Long marketplace_createListing(Long listingId, boolean showOnProfile, MarketListing listing) throws FacebookException, IOException;
  
  /**
   * Create a new marketplace listing.
   *
   * @param showOnProfile set to true to show the listing on the user's profile, set to false to prevent the listing from being shown on the profile.
   * @param listing the listing to publish.
   *
   * @return the id of the listing created (or modified).
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public Long marketplace_createListing(boolean showOnProfile, MarketListing listing) throws FacebookException, IOException;
  
  /**
   * Return a list of all valid Marketplace subcategories.
   *
   * @return a list of marketplace subcategories allowed by Facebook.
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public List<String> marketplace_getSubCategories() throws FacebookException, IOException;
  
  /**
   * Retrieve listings from the marketplace.  The listings can be filtered by listing-id or user-id (or both).
   *
   * @param listingIds the ids of listings to filter by, only listings matching the specified ids will be returned.
   * @param uids the ids of users to filter by, only listings submitted by those users will be returned.
   *
   * @return A list of marketplace listings that meet the specified filter criteria.
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public List<Listing> marketplace_getListings(List<Long> listingIds, List<Long> uids) throws FacebookException, IOException;
  
  /**
   * Search the marketplace listings by category, subcategory, and keyword.
   *
   * @param category the category to search in, optional (unless subcategory is specified).
   * @param subcategory the subcategory to search in, optional.
   * @param searchTerm the keyword to search for, optional.
   *
   * @return a list of marketplace entries that match the specified search parameters.
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public List<Listing> marketplace_search(MarketListingCategory category, MarketListingSubcategory subcategory, String searchTerm) throws FacebookException, IOException;
  
  /**
   * Remove a listing from the marketplace by id.
   *
   * @param listingId the id of the listing to remove.
   * @param status the status to apply when removing the listing.  Should be one of MarketListingStatus.SUCCESS or MarketListingStatus.NOT_SUCCESS.
   *
   * @return true if the listing was successfully removed
   *         false otherwise
   *
   * @throws FacebookException if an error happens when executing the API call.
   * @throws IOException if a communication/network error happens.
   */
  public boolean marketplace_removeListing(Long listingId, MarketListingStatus status) throws FacebookException, IOException;
  
  /**
   * Modify a marketplace listing
   * @param listingId identifies the listing to be modified
   * @param showOnProfile whether the listing can be shown on the user's profile
   * @param attrs the properties of the listing
   * @return the id of the edited listing
   * @see MarketplaceListing
   * @see <a href="http://wiki.developers.facebook.com/index.php/Marketplace.createListing">
   *      Developers Wiki: marketplace.createListing</a>
   */
  public Long marketplace_editListing(Long listingId, Boolean showOnProfile, MarketListing attrs)
    throws FacebookException, IOException;
}
