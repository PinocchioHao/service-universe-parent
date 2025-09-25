import pandas as pd
import json

def extract_json_log(input_file, output_file="process_log.csv"):
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
    print(f"✅ 成功提取 {len(rows)} 条 JSON 日志，已保存为 {output_file}")

if __name__ == "__main__":
    input_file = input("请输入日志文件名（例如 log.txt）：").strip()
    extract_json_log(input_file)