#!/usr/bin/env bash
set -euo pipefail

# Copy app images from a source directory into Android's drawable-nodpi folder
# Usage: tools/import-assets.sh [source_dir]

SRC_DIR=${1:-assets-source}
DST_DIR=app/src/main/res/drawable-nodpi

mkdir -p "$DST_DIR"

echo "Importing assets from: $SRC_DIR -> $DST_DIR"

copy_or_warn() {
  local src_name="$1"     # e.g. read_with_intention.png
  local dst_name="$2"     # e.g. read_with_intention.png
  if [[ -f "$SRC_DIR/$src_name" ]]; then
    cp -f "$SRC_DIR/$src_name" "$DST_DIR/$dst_name"
    echo "✓ $dst_name"
  else
    echo "! Missing: $SRC_DIR/$src_name (will skip)"
  fi
}

# Onboarding illustrations
copy_or_warn read_with_intention.png read_with_intention.png
copy_or_warn reflect_and_remember.png reflect_and_remember.png
copy_or_warn build_gentle_habit.png build_gentle_habit.png

# Register illustration
copy_or_warn img_register_illustration.png img_register_illustration.png

# Home grid covers
copy_or_warn cover_davinci.png cover_davinci.png
copy_or_warn cover_carrie.png cover_carrie.png
copy_or_warn cover_good_sister.png cover_good_sister.png
copy_or_warn cover_waiting.png cover_waiting.png

# Detail hero
copy_or_warn img_detail_hero.png img_detail_hero.png

# Profile avatar
copy_or_warn img_avatar_default.png img_avatar_default.png

# Wordmark: prefer PNG for now
copy_or_warn logo_hold_that_thought.png logo_hold_that_thought.png

echo "Done. If any were missing, add them to $SRC_DIR and rerun."

