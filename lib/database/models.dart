import 'package:uuid/uuid.dart';

enum ChannelImportance {
  //Value signifying that the user has not expressed an importance. should not be used
  unspecified(-1000),
  //does not show in the shade
  none(0),
  //only shows in the shade, below the fold
  min(1),
  //shows in the shade, and potentially in the status bar
  low(2),
  //shows everywhere, makes noise, but does not visually intrude.
  def(3),
  //shows everywhere, makes noise and peeks.
  high(4),
  //unused
  max(5);

  const ChannelImportance(this.value);

  final int value;
}

enum ChannelVisibility {
  //value signifying that the user has not expressed a per-app visibility override value.
  noOverride(-1000),
  //does not show in the shade
  secret(-1),
  //show this notification on all lockscreens, but conceal sensitive or private information on secure lockscreens.
  private(0),
  //Do not reveal any part of this notification on a secure lockscreen.
  pubic(1);

  const ChannelVisibility(this.value);

  final int value;
}

abstract class Model {
  final Uuid? id;

  Model({this.id});

  @override
  String toString() {
    return '$runtimeType{id: $id}';
  }

  Map<String, dynamic> toMap();
}

class Channel extends Model {
  final String channelId;
  final String name;
  final String description;
  final ChannelImportance importance;
  final bool vibration;
  final bool ligths;
  final ChannelVisibility visibility;

  Channel({required this.channelId, required this.name, required this.description, required this.importance,
    required this.vibration, required this.ligths, required this.visibility});

  @override
  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'channelId': channelId,
      'name': name,
      'description': description,
      'importance': importance,
      'vibration': vibration,
      'ligths': ligths,
    };
  }
}

enum NotificationPriority {
  //these items might not be shown to the user except under special circumstances
  min(-2),
  //the UI may choose to show these items smaller
  low(-1),
  //default
  def(0),
  //the UI may choose to show these items larger
  high(1),
  //items that require the user's prompt attention or input
  max(2);

  const NotificationPriority(this.value);

  final int value;
}

class Notification extends Model {
  final int activeId;
  final Uuid channelId;
  final String contentTitle;
  final String contentText;
  final NotificationPriority priority;
  final bool autoCancel;

  Notification({required this.activeId, required this.channelId, required this.contentTitle,
    required this.contentText, required this.priority, required this.autoCancel});

  @override
  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'channelId': channelId,
      'contentTitle': contentTitle,
      'contentText': contentText,
      'priority': priority,
      'autoCancel': autoCancel,
    };
  }
}
