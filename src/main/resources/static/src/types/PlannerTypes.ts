export interface IPlannerRequest {
    origin: string,
    destination: string,
    fare: number,
    origDateTime: string,
    destDateTime: string,
    clipper: number,
    tripTime: number,
    co2: number
}

export interface ISchedule {
    before: number, 
    after: number,
    request: IPlannerRequest[]
}

export interface IPlannerState {
    plans: any;
}