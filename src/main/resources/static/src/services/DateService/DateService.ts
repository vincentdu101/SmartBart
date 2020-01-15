export namespace DateService {

    export function printFormattedTime(dateString: string): string {
        let date = new Date(dateString);
        return date.toLocaleTimeString();
    }

}