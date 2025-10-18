import pandas as pd
import json

def extract_json_log(input_file, output_file="booking_process_log.csv"):
    rows = []
    with open(input_file, "r") as f:
        for line in f:
            line = line.strip()
            if not line:
                continue
            try:
                event = json.loads(line)
                rows.append({
                    "Case ID": event["caseId"],
                    "Activity": event["activity"],
                    "Timestamp": event["timestamp"],
                    "Resource": event["resource"]
                })
            except json.JSONDecodeError:
                continue

    df = pd.DataFrame(rows)
    df.to_csv(output_file, index=False)
    print(f"✅ Successfully extracted {len(rows)} JSON log entries and saved to {output_file}")
if __name__ == "__main__":
    input_file = input("Please enter the log file name (e.g., log.txt): ").strip()
    extract_json_log(input_file)